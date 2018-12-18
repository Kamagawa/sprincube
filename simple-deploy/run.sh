#!/usr/bin/env bash
docker build -f account.Dockerfile -t account:latest .
docker build -f friend.Dockerfile -t friend:latest .
docker build -f bff.Dockerfile -t bff:latest .
kubectl apply -f account.yml
kubectl apply -f friend.yml
kubectl apply -f bff.yml

