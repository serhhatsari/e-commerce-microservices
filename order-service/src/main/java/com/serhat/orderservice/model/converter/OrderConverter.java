package com.serhat.orderservice.model.converter;

import com.serhat.orderservice.constant.OrderStatus;
import com.serhat.orderservice.model.dto.request.OrderAddRequest;
import com.serhat.orderservice.model.dto.request.OrderItemAddRequest;
import com.serhat.orderservice.model.dto.response.OrderDto;
import com.serhat.orderservice.model.dto.response.OrderItemDto;
import com.serhat.orderservice.model.entity.OrderEntity;
import com.serhat.orderservice.model.entity.OrderItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter {


    // Entity to DTO
    public static OrderDto toOrderDto(OrderEntity orderEntity) {

        return OrderDto.builder()
                .id(orderEntity.getId())
                .customerId(orderEntity.getCustomerId())
                .totalAmount(orderEntity.getTotalAmount())
                .orderDate(orderEntity.getOrderDate())
                .orderStatus(orderEntity.getOrderStatus())
                .orderItems(toOrderItemDtoList(orderEntity.getOrderItems()))
                .build();
    }

    public static List<OrderItemDto> toOrderItemDtoList(List<OrderItemEntity> orderItemEntities) {
        return orderItemEntities.stream().map(OrderConverter::toOrderItemDto).collect(Collectors.toList());
    }

    public static OrderItemDto toOrderItemDto(OrderItemEntity orderItemEntity) {
        return OrderItemDto.builder()
                .id(orderItemEntity.getId())
                .productId(orderItemEntity.getProductId())
                .quantity(orderItemEntity.getQuantity())
                .price(orderItemEntity.getPrice())
                .build();
    }


    // Request to Entity
    public static OrderEntity toOrderEntity(OrderAddRequest orderAddRequest) {
       OrderEntity orderEntity = OrderEntity.builder()
                .customerId(orderAddRequest.getCustomerId())
                .totalAmount(orderAddRequest.getTotalAmount())
                .orderDate(orderAddRequest.getOrderDate())
                .orderStatus(OrderStatus.PLACED)
                .build();

        orderEntity.setOrderItems(toOrderItemEntityList(orderAddRequest.getOrderItems(), orderEntity));
        return orderEntity;
    }

    public static List<OrderItemEntity> toOrderItemEntityList(List<OrderItemAddRequest> orderItemAddRequests, OrderEntity orderEntity) {
        return orderItemAddRequests.stream().map(order -> toOrderItemEntity(order, orderEntity)).collect(Collectors.toList());
    }

    public static OrderItemEntity toOrderItemEntity(OrderItemAddRequest orderItemAddRequests, OrderEntity orderEntity) {
        return OrderItemEntity.builder()
                .productId(orderItemAddRequests.getProductId())
                .quantity(orderItemAddRequests.getQuantity())
                .price(orderItemAddRequests.getPrice())
                .order(orderEntity)
                .build();
    }


}
