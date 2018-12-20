# Ymal File Structure(best practice)

[Official Docs](https://kubernetes.io/docs/concepts/configuration/overview/) 

# Some Common Sense
always put environment related deployment first. This means path config, secrets, and service entry will be deployed before
service, pod and deployment. This is because, a deployment will need to rely on variables supplied from environment and 
cannot pick up newly added configurations on the fly.
 
# C


# Configuration Best practice
1. Specify latest stable API verison
2. Config files sore in version control before deploy in the cluster, this allows quick roo back and recreation, restoration
3. Write in YAML not JSON
4. ```Group related into one file whenever it makes sense```, since one file is easier to manage one file
5. can call ```kubectl apply -f <directory>``` on a collection of object
6. put description in annotation
 
 


