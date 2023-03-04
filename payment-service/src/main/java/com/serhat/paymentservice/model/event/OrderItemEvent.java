package com.serhat.paymentservice.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEvent implements Serializable {
    private Long id;

    private Long productId;

    private Integer quantity;

    private Double price;

}
