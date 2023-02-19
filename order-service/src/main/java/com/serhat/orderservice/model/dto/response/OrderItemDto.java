package com.serhat.orderservice.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serhat.orderservice.model.dto.response.OrderDto;
import com.serhat.orderservice.model.entity.OrderEntity;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class OrderItemDto implements Serializable {

    private Long id;

    @JsonProperty("product_id")
    private Long productId;

    private Integer quantity;
    private Double price;

}
