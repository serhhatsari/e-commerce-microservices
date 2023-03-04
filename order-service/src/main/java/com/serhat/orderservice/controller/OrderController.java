package com.serhat.orderservice.controller;


import com.serhat.orderservice.model.dto.request.OrderAddRequest;
import com.serhat.orderservice.model.dto.response.OrderDto;
import com.serhat.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        log.debug("/api/v1/orders is called with GET method");
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Long id) {
        log.debug("/api/v1/orders/{} is called with GET method", id);
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid  OrderAddRequest orderAddRequest) {
        log.debug("/api/v1/orders is called with POST method");
        return new ResponseEntity<>(orderService.createOrder(orderAddRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("id") Long id, @RequestBody @Valid OrderAddRequest orderAddRequest) {
        log.debug("/api/v1/orders/{} is called with PUT method", id);
        return new ResponseEntity<>(orderService.updateOrder(id, orderAddRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
        log.debug("/api/v1/orders/{} is called with DELETE method", id);
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
