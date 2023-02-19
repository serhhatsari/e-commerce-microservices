package com.serhat.orderservice.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    @JsonProperty("order_date")
    private LocalDateTime orderDate;

    @JsonProperty("order_status")
    private OrderStatus orderStatus;

    @JsonProperty("order_items")
    private List<OrderItemDto> orderItems;

}
