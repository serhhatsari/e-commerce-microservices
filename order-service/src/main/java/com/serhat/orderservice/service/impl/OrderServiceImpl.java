package com.serhat.orderservice.service.impl;

import com.serhat.orderservice.model.dto.OrderDto;
import com.serhat.orderservice.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Override
    public List<OrderDto> getAllOrders() {
        return null;
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return null;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        return null;
    }

    @Override
    public void deleteOrder(Long id) {

    }
}
