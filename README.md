# Description
Implementation of a REST Microservices for an E-Commerce Project with Spring Boot.  

Technologies used in this project: MySQL, MongoDB, PostgreSQL, Redis, Kafka, Prometheus, Grafana, Docker, Docker Compose, Spring Cloud Gateway

## Getting Started

### Dependencies
* Maven
* Java 17
* Docker / Docker Compose

### Installing
You can clone the project using the following command:    
`git clone https://github.com/serhhatsari/e-commerce-backend`  

### Executing program
First, you need to build every service by running the build script:  
`cd scripts && chmod +x build.sh && ./build.sh && cd ..`

Then, you can run the project using docker compose:    
`docker compose up --build`
