package com.serhat.customerservice;

import com.serhat.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CustomerServiceApplication {
	public static void main(String[] args) {

		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
