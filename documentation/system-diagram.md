# System Diagram

## Sprincube call and data flowchart

![](media/path.png)
First the user makes a call and it it is been sent through ingress gateway. Then the bff service which listens at 
the ingress gate way sends the request into subsequent services. The services can send communicate with each other 
and so can they listen directly from the ingress gateway. All processing data are again sent to telemetry. 

## Three Senarios of Database: 
#### Senario one: external db out of the mesh
[![](media/mysql.svg)](https://istio.io/blog/2018/egress-tcp/)
the databse is hosted externally outside of kubernetes outside of mesh. It uses a service entry to enter the external databse

#### Senario two, Mesh Expansion:
[![](media/meshexp.png)](https://medium.com/ibm-cloud/istio-mesh-expansion-on-ibm-cloud-private-c335eabf7990)
The database is hosted outside of kubernetes and local machine, but is part of the Istio mesh through Mesh expansion.
This elevate the database to be elevated as a super member meanwhile not requiring it to be spawned as a node in
kubernetes.

#### Scenario three, spawn up a deployment in kubernetes
[![](media/internaldb.png)](https://kubernetes.io/docs/concepts/storage/persistent-volumes/)
spawn a kubernetes deployment of mysql and points it to a persistence claim