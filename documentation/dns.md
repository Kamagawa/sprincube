
# DNS and Service Discovery 
In kubernetes, service discovery is done through DNS (Domain Name System) server. They create a set of 
DNS record is created for each new service that’s created

When looking up services in DNS server, run a DNS query for the service name and namespace. 
Each service is registered under the namespace, and to access services from the same name space uses the 
`<service name>`, if from a different name space but same cluster use `<service_name>.<namespace>`

The full path is `local.cluster.<namespace>.<service>`
For example in bff, the service call account from the same cluster and same name space, so it could `account` directly.
However spring would not recognize `account` string by itself and would force a valid protocal url as an input. 
So be sure to use `http://account` 

By default, the service name is metadata.name, but if a host name is specified, it takes precedence over 
the metadata name and becomes the service name.
## Kube-DNS, Ingress, Envoy
By default, Kubernetes, and the istio on top of it uses Kube-DNS to assign a name to the Microservice Service. The name is named in the selector section of service deployment. Then when traffic comes through the Ingress Gateway, Envy performs a service discovery to route user to the desired location. In the mean time, performing load balancing and security. 

Here are the two major appraoches through default method:

 
### Container Level Port Forwarding 
This is the most commonly used method found online. The Approach was to rely on Kube-DNS to poiint to the microservice's default ':80' port. Then in service route it to the application port. 
```yaml
kind: Service
apiVersion: v1
metadata:
  name: my-service
spec:
  selector:
    app: MyApp
  ports:
  - name: http
    protocol: TCP
    port: 80
    targetPort: 9376
  externalIPs:
  - 80.11.12.10
```
Here the deployment container accept connection on default 80 port.Then forward the connection to port '9376' on the application level. 

This method works the best when each microservice correspond to one port. The deployer of this microservice knows which port they shall forward to on the application level. So that when developer calls a service, they do not need to be concern about the port to call.

However, this method force every call on container level to 80.

### Port Naming
Aside from container level port forwarding, one can name the port at of "http" and run a query for DNS SRV record by "_http._tcp.my-service.my-ns" to discover the port "http".

### DNS SRV Record


## CoreDNS
Core DNS is a replacement to the kube-DNS, it offers "chained plugin", which means, it supports mutiple plugins, which also means, it opens up the option to program customizable plugin. CoreDNS could be used to give a higer degree of freedom when we need to handle our own DNS service discovery.

## Instana
instanna is a sensor based plugin for kubernetes that enables auto discover and monitor of microservices 

## Test on DNS Records: 
run this: 
```bash
$ kubectl run -i -t --image=infoblox/dnstools:k8sblog --restart=Never dnstest

Waiting for pod default/dnstest to be running, status is Pending, pod ready: false
If you don't see a command prompt, try pressing enter.

# curl nginx.default.svc.cluster.local
```

