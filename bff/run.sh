#!/usr/bin/env bash
gradle build bootjar
docker build -t bff:latest .
kubectl apply -f bff.yml
kubectl apply -f gateway.yml
