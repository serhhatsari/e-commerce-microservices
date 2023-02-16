package com.serhat.orderservice.service.impl;

import com.serhat.orderservice.constant.OrderStatus;
import com.serhat.orderservice.model.converter.OrderConverter;
import com.serhat.orderservice.model.dto.request.OrderAddRequest;
import com.serhat.orderservice.model.dto.response.OrderDto;
import com.serhat.orderservice.model.entity.OrderItemEntity;
import com.serhat.orderservice.repository.OrderRepository;
import com.serhat.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(OrderConverter::toOrderDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return orderRepository.findById(id).map(OrderConverter::toOrderDto).orElse(null);
    }

    @Override
    public OrderDto createOrder(OrderAddRequest orderAddRequest) {
        return OrderConverter.toOrderDto(orderRepository.save(OrderConverter.toOrderEntity(orderAddRequest)));
    }

    @Override
    public OrderDto updateOrder(Long id, OrderAddRequest orderAddRequest) {
        return orderRepository.findById(id).map(order -> {
            order.setOrderStatus(OrderStatus.PLACED);
            order.setOrderDate(orderAddRequest.getOrderDate());
            order.setTotalAmount(orderAddRequest.getTotalAmount());
            order.setOrderItems(OrderConverter.toOrderItemEntityList(orderAddRequest.getOrderItems(), order));
            return OrderConverter.toOrderDto(orderRepository.save(order));
        }).orElse(null);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
