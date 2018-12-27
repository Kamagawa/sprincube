## Traffic Management:

In Kubernetes, docker images are being deployed as pods and exposed as services. In Istio, traffic from service
to service are monitored and managed by Istio Pilot. To do so, a virtual service is deployed and it will control
the incoming and outgoing traffic. Along with that, destinationrules provide specific routing information for the 
virtualservice. At the same time, load balancing between different versions are covered as well.

## Versions:

All deployed pods have the option to include labels in their config files. Version is common label that are often
used when developers want to update a deployed pod. To do this, a version label is included in the configuration
file when pods are being created. 
```yaml
kind: Service
apiVersion: v1
metadata:
  name: account-service
  labels: 
    app: account-service
spec:
  selector:
    app: account-service
  ports:
  - name: http
    port: 8082
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: account-service-v1
spec:
  replicas: 1
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
Here is the configuration deploying a service and deployment for Account-service.

Now if we want to deploy a new version for account-service, we build a new image and write a new configuration
to create the service and deployment using the new image.
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: account-service-v2
spec:
  replicas: 1
  template:
    metadata:
      labels:
        app: account-service
        version: v2
    spec:
      containers:
      - name: account-service
        image: account-service:v2
        imagePullPolicy: IfNotPresent
        ports:
        - containerPort: 8082
```
When the new version is deployed, a virtual service and corresponding destination rule are needed 
to help direct traffic into the two deployments. The method of traffic directing can be specified in virtual service
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
          subset: v0
        weight: 10
      - destination:
          host: account-service
          subset: v1
        weight: 90
---
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

In here, the virtual service will have a default round-robin load balancing rule to direct traffic
to the two different versions of account-service. The destination rule allows the virtual service
to distinguish between the two versions, by matching the labels for the deployments.

There are other ways to split the traffic. For example, only certain users can access the new version
```yaml
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: account-service
spec:
  hosts:
  - account-service
  http:
  - match:
    - headers:
        end-user:
          exact: admin
    route:
    - destination:
        host: account-service
        subset: v2
  - route:
    - destination:
        host: account-service
        subset: v1

```

Here, only admin users can access the new version, and all other users still access the previous version.

To minimize the impact that the new version brings to the services, we only delegate a portion of the traffic
to the new version. If the service is stable and consistent, we can remove the old version and delegate
all the traffic to the new version.