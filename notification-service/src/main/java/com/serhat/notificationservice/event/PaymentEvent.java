package com.serhat.notificationservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEvent implements Serializable {

    private Long paymentId;
    private Long orderId;
    private Long customerId;
    private Double amount;
}
