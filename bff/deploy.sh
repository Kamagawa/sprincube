#!/bin/bash
gradle clean build

kubectl delete deploy bff
eval $(minikube docker-env)
docker build -t bff:v0 .
kubectl apply -f userapp.yml