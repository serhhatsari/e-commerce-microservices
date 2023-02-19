package com.serhat.orderservice.model.dto.response;

import com.serhat.orderservice.constant.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class OrderDto implements Serializable {

    private Long id;

    private Long customerId;

    private BigDecimal totalAmount;

    private LocalDateTime orderDate;

    private OrderStatus orderStatus;

    private List<OrderItemDto> orderItems;

}
