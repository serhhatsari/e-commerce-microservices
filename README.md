# Description
Implementation of a REST Microservices for an E-Commerce Project with Spring Boot.  

**Tech Stack**: MySQL, MongoDB, PostgreSQL, Redis, Kafka, Prometheus, Grafana, Docker, Docker Compose, Elastic Search, Logstash, Kibana, Spring Cloud Gateway, Swagger

## Getting Started

### Dependencies
* Maven
* Java 17
* Docker / Docker Compose

### Installing
You can clone the project using the following command:    
```shell
git clone https://github.com/serhhatsari/e-commerce-backend
```  

### Executing program
First, you need to build every service by running the build script:  
```shell
cd scripts && chmod +x build.sh && ./build.sh && cd ..
```

Then, you can run the project using docker compose:    
```shell
docker compose up --build
```

## Project Architecture  
![Project Architecture](docs/architechture.png)

### Some Important Ports  
* Gateaway: 8080
* Kafka UI: 9093
* Grafana: 3000
* Kibana: 5601