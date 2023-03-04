package com.serhat.orderservice.service.impl;

import com.serhat.orderservice.constant.OrderStatus;
import com.serhat.orderservice.model.event.OrderPlacedEvent;
import com.serhat.orderservice.exception.OrderNotFoundException;
import com.serhat.orderservice.model.converter.OrderConverter;
import com.serhat.orderservice.model.dto.request.OrderAddRequest;
import com.serhat.orderservice.model.dto.response.OrderDto;
import com.serhat.orderservice.model.entity.OrderEntity;
import com.serhat.orderservice.repository.OrderRepository;
import com.serhat.orderservice.service.OrderService;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    private final MeterRegistry meterRegistry;

    @Override
    @Cacheable(value = "orders")
    public List<OrderDto> getAllOrders() {
        meterRegistry.counter("order-service", "method", "getAllOrders").increment();
        log.debug("Get all orders is called");
        return orderRepository.findAll().stream().map(OrderConverter::toOrderDto).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "orders", key = "#id")
    public OrderDto getOrderById(Long id) {
        meterRegistry.counter("order-service", "method", "getOrderById").increment();
        log.debug("Get order by id is called");
        return orderRepository.findById(id).map(OrderConverter::toOrderDto).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    @CacheEvict(value = "orders", allEntries = true)
    public OrderDto createOrder(OrderAddRequest orderAddRequest) {
        meterRegistry.counter("order-service", "method", "createOrder").increment();
        log.debug("Create order is called");
        OrderEntity orderEntity = orderRepository.save(OrderConverter.toOrderEntity(orderAddRequest));
        kafkaTemplate.send("order-events", new OrderPlacedEvent(orderEntity.getId(), orderEntity.getCustomerId(), orderEntity.getTotalAmount(), OrderConverter.toOrderItemEventList(orderEntity.getOrderItems())));
        return OrderConverter.toOrderDto(orderEntity);
    }

    @Override
    @CachePut(value = "orders", key = "#id")
    public OrderDto updateOrder(Long id, OrderAddRequest orderAddRequest) {
        meterRegistry.counter("order-service", "method", "updateOrder").increment();
        log.debug("Update order is called");
        log.info("Update order is called");
        return orderRepository.findById(id).map(order -> {
            order.setOrderStatus(OrderStatus.PLACED);
            order.setOrderDate(orderAddRequest.getOrderDate());
            order.setTotalAmount(orderAddRequest.getTotalAmount());
            order.setOrderItems(OrderConverter.toOrderItemEntityList(orderAddRequest.getOrderItems(), order));
            return OrderConverter.toOrderDto(orderRepository.save(order));
        }).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    @CacheEvict(value = "orders", allEntries = true)
    public void deleteOrder(Long id) {
        meterRegistry.counter("order-service", "method", "deleteOrder").increment();
        log.debug("Order with id {} is deleted", id);
        log.info("Order with id {} is deleted", id);
        orderRepository.deleteById(id);
    }
}
