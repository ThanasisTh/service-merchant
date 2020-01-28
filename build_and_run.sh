#!/bin/bash

set -e

sudo docker stop service-merchant

mvn clean test package

sudo docker build --tag service-merchant .
sudo docker-compose up -d