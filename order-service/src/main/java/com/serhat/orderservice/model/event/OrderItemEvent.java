package com.serhat.orderservice.model.event;

import com.serhat.orderservice.model.entity.OrderItemEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
public class OrderItemEvent implements Serializable {
    private Long id;

    private Long productId;

    private Integer quantity;

    private Double price;



}
