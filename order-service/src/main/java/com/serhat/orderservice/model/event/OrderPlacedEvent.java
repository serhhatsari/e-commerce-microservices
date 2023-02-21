package com.serhat.orderservice.model.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent {

        private Long orderId;
        private Long customerId;
        private BigDecimal totalAmount;
}
