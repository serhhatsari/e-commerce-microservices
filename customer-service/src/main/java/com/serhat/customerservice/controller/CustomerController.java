package com.serhat.customerservice.controller;

import com.serhat.customerservice.model.dto.request.CustomerAddRequest;
import com.serhat.customerservice.model.dto.response.CustomerDto;
import com.serhat.customerservice.model.entity.CustomerEntity;
import com.serhat.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerAddRequest customerAddRequest) {
        return new ResponseEntity<>(customerService.createCustomer(customerAddRequest),  HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable String id) {
        return new ResponseEntity<>(customerService.getCustomer(id),  HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String id, @RequestBody CustomerAddRequest customerAddRequest) {
        return new ResponseEntity<>(customerService.updateCustomer(id, customerAddRequest),  HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*
    @GetMapping("/:id/orders")
    public ResponseEntity<List<OrderDto>> getCustomerOrders(@PathVariable String id) {
        return new ResponseEntity<>(customerService.getCustomerOrders(id),  HttpStatus.OK);
    }*/


}
