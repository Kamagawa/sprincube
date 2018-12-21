##Circuit Breaking Test Scenario

Assuming Kubernetes cluster has been deployed and Istio has been injected. This can be checked
by running `kubectl get pods` and make sure the pods are all running. Also, create the service for
each pod and check that the services are running by using `kubectl get services`

Set up two versions of the same service with the same yaml file but different labels and use different images.

Also, create a second replica for one of the deployment.
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: account-service-v1
spec:
  replicas: 2
  template:
    metadata:
      labels:
        app: account-service
        version: v1
    spec:
      containers:
        - name: account-service
          image: account-service:v1
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8082
```

Then set up a virtual service for the services.
```yaml
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: account-service
spec:
  hosts:
    - account-service
  http:
  - route:
    - destination:
        host: account-service
        subset: v1
    - destination:
        host: account-service
        subset: v2
```

This will allow the traffic to be sent to the two services in a round-robin fashion. Also the traffic will be
split between the two replicas for version 1.

Also set up the destination rule configuration to allow the virtual service to
distinguish between the two different versions
```yaml
apiVersion: networking.istio.io/v1alpha3
kind: DestinationRule
metadata:
  name: account-service
spec:
  host: account-service
  subsets:
  - name: v1
    labels:
      version: v1
  - name: v2
    labels:
      version: v2
```

Set up a gateway and BFF service using the method in the Traffic Management section to
help with testing the circuit breaking functions.

At this point, if the user tries to access the Account service, the traffic will be routed between
the two versions in te default round-robin fashion. For traffics going to version 1, it will also be
delegated between the two replicas in round-robin fashion.

Now if we take down one of the replicas, which can be done either be killing the pod or doing fault
injection. Either way, the pod will now fail whenever traffic is delegate to it and now we use circuit
breaking features to ensure that even with a failing pod, the calls will still not fail.

To add in circuit breaking feature, fist modify the virtual service config to allow retries on failure
```yaml
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  creationTimestamp: null
  name: account-service
spec:
  hosts:
  - account-service
  http:
  - retries:
      attempts: 3
      perTryTimeout: 4.000s
    route:
    - destination:
        host: account-service
        subset: version-v1
    - destination:
        host: account-service
        subset: version-v2
```

This config will let Istio retry a connection to the service 3 times upon failure. Each time, the service
has 4 seconds to respond.

Modify the destination rule config to allow pool ejection.
```yaml
apiVersion: networking.istio.io/v1alpha3
kind: DestinationRule
metadata:
  creationTimestamp: null
  name: account-service
spec:
  host: account-service
  subsets:
  - labels:
      version: v1
    name: version-v1
    trafficPolicy:
      connectionPool:
        http: {}
        tcp: {}
      loadBalancer:
        simple: RANDOM
      outlierDetection:
        baseEjectionTime: 15.000s
        consecutiveErrors: 1
        interval: 5.000s
        maxEjectionPercent: 100
  - labels:
      version: v2
    name: version-v2
    trafficPolicy:
      connectionPool:
        http: {}
        tcp: {}
      loadBalancer:
        simple: RANDOM
      outlierDetection:
        baseEjectionTime: 15.000s
        consecutiveErrors: 1
        interval: 5.000s
        maxEjectionPercent: 100
```

This config will eject the service from the load balancing pool if service calls failed.

The outlierDetection part of this file is the config that allows Istio to detect the faulty pod
and remove it from the load balancing pool so it is not bombarded with more traffic while it is down.

Combining with the retry config defined in the virtual service config, if the service call failed, it is
ejected from the pool and the retry calls will be sent to the other replica which is healthy.

This example showcase Istio's capability to handle faulty services and in the case that a pod failed,
the calls will still be retried and redirected to a healthy pod so the user will still receive a successful result.
