package com.serhat.orderservice.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.serhat.orderservice.constant.OrderStatus;
import com.serhat.orderservice.model.dto.response.OrderItemDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@Data
public class OrderAddRequest {

    @NotNull(message = "Customer Id cannot be null")
    private Long customerId;

    @DecimalMin(value = "0.0", message = "Total amount must be greater than or equal to 0")
    private BigDecimal totalAmount;

    @NotNull(message = "Order date cannot be null")
    private LocalDateTime orderDate;

    @NotEmpty(message = "Order Items cannot be empty")
    @Valid
    private List<OrderItemAddRequest> orderItems;

}
