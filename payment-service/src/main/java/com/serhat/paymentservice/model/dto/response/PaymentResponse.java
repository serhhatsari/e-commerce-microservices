package com.serhat.paymentservice.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serhat.paymentservice.constant.PaymentStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private Long id;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("customer_id")
    private Long customerId;

    private Double amount;

    @JsonProperty("payment_status")
    private PaymentStatus paymentStatus;
}