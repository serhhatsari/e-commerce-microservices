package com.serhat.orderservice.model.event;


import com.serhat.orderservice.model.entity.OrderItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent implements Serializable {

        private Long orderId;
        private Long customerId;
        private BigDecimal totalAmount;

        private List<OrderItemEvent> orderItems;
}
