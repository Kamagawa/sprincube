#!/usr/bin/env bash
gradle build bootjar
docker build -t friend:latest .

kubectl apply -f friend.yml
kubectl apply -f mysql03-deployment.yml
kubectl apply -f serviceentry.yml