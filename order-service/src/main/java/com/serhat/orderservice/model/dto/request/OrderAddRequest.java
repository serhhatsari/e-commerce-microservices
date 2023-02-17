package com.serhat.orderservice.model.dto.request;

import com.serhat.orderservice.constant.OrderStatus;
import com.serhat.orderservice.model.dto.response.OrderItemDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class OrderAddRequest {

    @NotEmpty(message = "Customer Id cannot be empty")
    private Long customerId;

    @NotEmpty(message = "Total Amount cannot be empty")
    private BigDecimal totalAmount;

    @NotEmpty(message = "Order Date cannot be empty")
    private LocalDateTime orderDate;

    @NotEmpty(message = "Order Status cannot be empty")
    private List<OrderItemAddRequest> orderItems;

}
