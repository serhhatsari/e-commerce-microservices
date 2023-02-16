package com.serhat.orderservice.model.dto.response;

import com.serhat.orderservice.model.dto.response.OrderDto;
import com.serhat.orderservice.model.entity.OrderEntity;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderItemDto {

    private Long id;
    private Long productId;
    private Integer quantity;
    private Double price;

}
