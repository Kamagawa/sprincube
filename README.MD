Istio Exploration 'Sprincube'
---------------------------
SpringCube is an exploratory project to explore the usage benefits as well as the short-commings of microservice architecture,
kubernetes Orchistration System, and Istio Service Mash. Sprincube, by itself, is an social app that showcase a person's account and his friends. Composed of 3 services, friend and 
account service provides the information, and bff aggregates the information obtained from the 2 atomic services. 

# Simple deploy
Simple deploy packs existing jar files into images, then deploy onto kubernetes. 
To proceed, open 'simple-deploy' folder, run the following command in terminal(bash,terminal): 
([before that, ensure you have Istio installed and sidecar enabled]())
```bash
minikube start
minikube docker-env
// istio add here
kubectl apply -f deploy.yml
./run.sh
``` 
Note: only move on after each successive comand are successful.
- The `./run.sh` command execute a shell script that builds images locally from the .jar file in the repository
- Then it execute `kubectl apply -f deploy.yml` to deploy configuration file that applies the images on kubernetes

# Complete Deploy
Complete deploy also includes build project into jar from its java source code locally, run the following commands here:
before that, ensure you have [istio installed and sidecar enabled](documentation)
```bash
minikube start
minikube docker-env
// istio add here
kubectl apply -f deploy.yml
./run.sh
```

# Documentation


