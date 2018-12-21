
# Deploy application on kubernetes with Istio:
1. Build the project into a executable, in java it would be 
`gradle clean build` or [`mvn clean compile assembly:single`](https://stackoverflow.com/a/574650/5378109)

2. Write a Dockerfile that wraps the jar and create a docker image by running 
`docker build –t <image_name:version> <directory_of_Dockerfile>`.

3. To expediently deploy the image in Kubernetes as a deployment and service, 

    a. `kubectl run <deployment_name> --image=<image_name:version> --port=<port>` and this will create the deployment 
along with a pod. 
    
    b. expose the deployment as a service, `kubectl expose deployment \<deployment_name\> --type=<type>`.

    c. As for 'type', `clusterIP`(default): exposes internally, cannot be accessed from outside the VM. 
    <b>`NodePort`</b>: expose externally and can be accessed from local browser. 
    `LoadBalancer`:  if the service has multiple deployments (ex. Multiple versions), the load balancer 
    service will balance the incoming traffic and direct it to a certain version, depending on the rule specified for traffic routing.

4. To deploy the "best practice way", put specification into deployment.yaml files and run them:

    a.   For making the deployment (kind = deployment) and the service (kind = service), specify name and 
  label so they can be identified by other services with the label.

    b.   For the deployment section, the number of replicas can be set if one wishes to deploy multiple 
  instances of the app. Also need to specify the name and version of the image that is to be used, 
  along with the port for this container.

    c.   For the service section. Specify the deployment that is being exposed with 
  `spec.selector.app=<deployment label>`. Also include the port and protocol for accessing the service.

5. Install Istio, download first, then `kubectl apply –f install/kubernetes/istio-demo.yml`. Now, all 
the features should be installed in the istio-system namespace (can be accessed by adding the tag “–n
    istio-system” after commands)

    -   When using ingress gateway in deploying with Istio, the ingress host is (minikube ip) and port 
    is found in (kubectl describe service istio-ingressgateway) under the port with name http2. The secured host is
    under the port with name https.

    -   Go to `INGRESS_HOST:INGRESS_PORT/productpage` to access the application.
    (/productpage is endpoints specified in by the ingress gateway yaml file)

    -   `Istioctl kube-inject –f <input_filename> -o <output_filename>` takes the
    input file and inject istio sidecar envoy into the service. `Kubectl create
    –f <outpu_filename>` then takes the file and create a service with it.