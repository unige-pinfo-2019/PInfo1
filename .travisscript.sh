#!/bin/bash

docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
docker tag unige/ad-service $DOCKER_USERNAME/ad-service
docker tag unige/items-service $DOCKER_USERNAME/items-service
docker tag unige/api-gateway $DOCKER_USERNAME/api-gateway
docker tag unige/user-service $DOCKER_USERNAME/user-service
docker tag unige/statistic-service $DOCKER_USERNAME/statistic-service
docker tag unige/messanger-service $DOCKER_USERNAME/messanger-service
docker push $DOCKER_USERNAME/ad-service
docker push $DOCKER_USERNAME/items-service
docker push $DOCKER_USERNAME/user-service
docker push $DOCKER_USERNAME/statistic-service
docker push $DOCKER_USERNAME/messanger-service
docker push $DOCKER_USERNAME/api-gateway
