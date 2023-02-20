package com.serhat.customerservice.controller;

import com.serhat.customerservice.model.dto.request.CustomerAddRequest;
import com.serhat.customerservice.model.dto.response.CustomerDto;
import com.serhat.customerservice.model.entity.CustomerEntity;
import com.serhat.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid  @RequestBody CustomerAddRequest customerAddRequest) {
        log.info("Request received to create customer: {}", customerAddRequest);
        return new ResponseEntity<>(customerService.createCustomer(customerAddRequest),  HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable String id) {
        log.info("Request received to get customer with id: {}", id);
        return new ResponseEntity<>(customerService.getCustomer(id),  HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String id, @Valid @RequestBody CustomerAddRequest customerAddRequest) {
        log.info("Request received to update customer with id: {}", id);
        return new ResponseEntity<>(customerService.updateCustomer(id, customerAddRequest),  HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        log.info("Request received to delete customer with id: {}", id);
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
