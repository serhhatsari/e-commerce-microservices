package com.serhat.orderservice.service;

import com.serhat.orderservice.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id);

    OrderDto createOrder(OrderDto orderDto);

    OrderDto updateOrder(Long id, OrderDto orderDto);

    void deleteOrder(Long id);
}
