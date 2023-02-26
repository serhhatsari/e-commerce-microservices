package com.serhat.productservice.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEvent {
    private Long id;

    private Long productId;

    private Integer quantity;

    private Double price;

}
