package com.serhat.orderservice.model.dto.request;

import com.serhat.orderservice.model.dto.response.OrderDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemAddRequest {

    private Long productId;

    private Integer quantity;

    private Double price;
}
