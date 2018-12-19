# What is Istio

Istio Service mesh includes service discovery, load balancing, failure recovery, metrics and monitoring. 
Istio provides control over the service mesh to allow deploy service with load balancing, 
service to service authentication, monitoring without changing the service codes

Core Features

-   Traffic management

    -   Control the API calls between the services

    -   Simplifies configuration for circuit breaker, timeouts and retries,
        making more reliable calls

-   Security

    -   Istio manages authentication, authorization, and encryption of service
        communications

    -   Service communications are secured by default

-   Observability

    -   Robust tracing, monitoring, and logging

Pilot: Service Discovery using Kubernetes service registry, dynamic request
routing, circuit breaker,

Citadel: authentication service, could upgrade unencrypted traffic and control
service access, uses mTLS

Galley: API configuration for Istio

Envoy:

-   A proxy sidecar that is injected into each pod as a separate container.

-   Envoy intercepts all inbound and outbound api calls to and from the pod that
    it is injected in.

-   Envoy then perform service discovery and update their load balancing pool
    accordingly.

-   Failure recovery feature

Ingress Gateway:

-   In a gateway.yaml file, add the necessary info for a gateway and virtual
    service.

-   Gateway:

    -   Use the istio default ingress controller (spec.selector.istio =
        ingressgateway).

    -   Then set a port of 80 for http protocol. The reason for http protocol
        port to be 80 is because the default ingress gateway is configured to
        map port 31380 to port 80.

    -   Later when accessing the gateway, go to browser and type
        https://(minikube ip):31380

-   Virtual service:

    -   Specify the gateway connected to the virtual service as the name of the
        gateway set up previously

    -   Optionally add config for matching uri(can use exact or prefix)

    -   Then add a route destination. With the host being the name of the
        service that this virtual service is calling, and port being the port of
        the same service

    -   Refers to the destination rule to find the subset (version) of the
        destination host (deployment) that it is calling

-   Destination Rules(Mixer feature):

    -   Destination rules declare the subsets for the deployments, also referred
        to as the version for the deployments (pods).

    -   It define the subset as the pods with the matching labels (names,
        version, etc.)

    -   Destination acts as the reference for virtual services to determine the
        subset/version

-   Run kubectl apply –f \<gateway.yaml\> and the gateway and virtual service
    will be configured.

-   To see more about the gateway and virtual service that was just deployed,
    run kubectl get (gateways/virtualservices)

-   Load balance is included in the istio-ingress gateway. The default load
    balancing method is round robin

Mixer:

-   Flexible model for authorization policies and telemetry collections

-   Envoy sidecar calls Mixer to perform precondition checks and to report
    telemetry

-   Envoy sidecar has local cache so precondition checks can be done from cache

-   Also uses buffer for outgoing telemetry so it calls Mixer less often

-   Mixer separates backend systems (policies and telemetry) from the Istio
    system

-   Mixer consists of many adapters to allow interaction with the backend
    infrastructure

-   Mixer picks up attributes from the request, such as destination ip/service,
    source ip/service, path, size, time, etc.

-   Attributes can be used in a expression for evaluation

-   Uses configuration models to control the process authorization policy and
    telemetry collection

Denier(Mixer feature):

-   A rule is added for with specs that searches for a specific call to
    services. Once a call that matches the specs is being executed, the denier
    will come in effect and interfere with the call

-   The actions that can be taken by the denier includes returning a code and a
    message and it stops the call

-   Instances: (still need to research)

Istio-Auth:

-   Sends TLS certs to Envoy with each calls from API gateway to the services

Canary deployment:

-   First deploy the version-1 of the application along with the gateway and
    virtual service for it.

-   Also specify the destination rule to direct all traffic to verstion-1

-   When version-2 is deployed and service is created, modify the virtual
    service to add a new route.destination

    -   Now there are 2 destinations in the virtual service for the same host.
        To distinguish between the two, use a subset under each destination

    -   Can even add restriction for each destination, such as http header
        match, so the newer version can only be accessed by certain users

    -   Another method is to assign weights to each destination

-   Also need to update the destination rule file to add the new subset

Destination Policy:

-   Can be used to implement circuit breaker and pool ejection

-   Specifies the destination and circuitBreaker method of that location in case
    it fails

    -   Circuit breaker methods include consecutive errors (number of error
        before circuit breaker starts), sleep windows (shut down time), and more

-   Citadel: authentication service, could upgrade unencrypted traffic and
    control service access, uses mTLS

-   Galley: API configuration for Istio
