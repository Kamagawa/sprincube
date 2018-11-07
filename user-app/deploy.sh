#!/bin/bash
gradle clean build

kubectl delete deploy user-app
eval $(minikube docker-env)
docker build -t user-app:v0 .
kubectl apply -f userapp.yml