package com.serhat.orderservice.model.dto.request;

import com.serhat.orderservice.constant.OrderStatus;
import com.serhat.orderservice.model.dto.response.OrderItemDto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class OrderAddRequest {

    private Long customerId;

    private BigDecimal totalAmount;

    private LocalDateTime orderDate;

    private List<OrderItemAddRequest> orderItems;

}
