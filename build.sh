#!/bin/bash

services=("api-gateaway" "customer-service" "notification-service" "order-service" "payment-service" "product-service")

# iterate through each service and build
for service in "${services[@]}"
do
    cd ${service}
    mvn clean package -DskipTests
    cd ..
done
