package com.serhat.productservice.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent {

    private Long orderId;
    private Long customerId;
    private BigDecimal totalAmount;
    private List<OrderItemEvent> orderItems;
}