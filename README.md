[![Build Status](https://travis-ci.org/unige-pinfo-2019/PInfo1.svg?branch=master)](https://travis-ci.org/unige-pinfo-2019/PInfo1)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=unige-pinfo-2019_PInfo1&metric=alert_status)](https://sonarcloud.io/dashboard?id=unige-pinfo-2019_PInfo1)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=unige-pinfo-2019_PInfo1&metric=coverage)](https://sonarcloud.io/dashboard?id=unige-pinfo-2019_PInfo1)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=unige-pinfo-2019_PInfo1&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=unige-pinfo-2019_PInfo1)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=unige-pinfo-2019_PInfo1&metric=security_rating)](https://sonarcloud.io/dashboard?id=unige-pinfo-2019_PInfo1)

# Project

UNIbay

# Description

### Team name

Les Canards Laqués

### Project members + member's task

* Adrien Chabert :    	Team Master, Front+Back
* Tommaso Peletta :	SystemAdmin, Front+Back
* Jonathan Lo :		Front End
* Patrick Sardinha :	Front End
* Guillaume Comte :	Back End
* Loan Hérault :	Back End



### Local execution

You can run all your microservices locally by doing the following commands in order:

'''
sudo mvn clean install -Ppackage-docker-image

cd ../docker-compose

sudo docker-compose -f docker-compose-microservices.yml down

sudo docker-compose -f docker-compose-microservices.yml up


Nouveau terminal : cd ./web-ui

ng build --watch


Nouveau terminal : cd ./docker-compose

sudo docker-compose -f docker-compose-api-gw.yml down

sudo docker-compose -f docker-compose-api-gw.yml up
'''

Now you can surf by going on https://localhost






