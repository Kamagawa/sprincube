#!/usr/bin/bash
gradle build bootjar
docker build -t account:latest .
kubectl apply -f account.yml
// needs gradle, docker and kubectl installed and runnning
