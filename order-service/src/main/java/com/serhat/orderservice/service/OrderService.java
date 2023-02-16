package com.serhat.orderservice.service;

import com.serhat.orderservice.model.dto.request.OrderAddRequest;
import com.serhat.orderservice.model.dto.response.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id);

    OrderDto createOrder(OrderAddRequest orderDto);

    OrderDto updateOrder(Long id, OrderAddRequest orderDto);

    void deleteOrder(Long id);
}
