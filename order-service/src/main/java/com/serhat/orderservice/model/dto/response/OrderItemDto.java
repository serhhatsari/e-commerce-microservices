package com.serhat.orderservice.model.dto.response;

import com.serhat.orderservice.model.dto.response.OrderDto;
import com.serhat.orderservice.model.entity.OrderEntity;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class OrderItemDto implements Serializable {

    private Long id;
    private Long productId;
    private Integer quantity;
    private Double price;

}
