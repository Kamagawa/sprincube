# Autoscale

Kubernetes supports Auto-scaling out of the box. However, auto-scale depends on metrics server to determine when to trigger the scaling operation. 

To get set an existing deployment to autoscale, run 
```yaml
kubectl autoscale <pod_name> 
```
 
Autoscale failed because of unable to detect the CPU useage: "\<unknown\>/50%" 

This is because Kubernetes does not have a metrics server or unable to utilize its metrics server. To install a kubernetes metrics server, run: 
```yaml and error occurs for "CPU: <unknown>/50%"
kubectl apply -f https://raw.githubusercontent.com/kubernetes/kops/master/addons/metrics-server/v1.8.x.yaml
```
