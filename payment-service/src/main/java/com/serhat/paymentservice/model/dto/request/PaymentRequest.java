package com.serhat.paymentservice.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serhat.paymentservice.constant.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
    @JsonProperty("order_id")
    @Min(value = 0, message = "Order id must be greater than 0")
    @NotNull(message = "Order id must not be null")
    private Long orderId;

    @JsonProperty("customer_id")
    @Min(value = 0, message = "Customer id must be greater than 0")
    @NotNull(message = "Customer id must not be null")
    private Long customerId;

    @Min(value = 0, message = "Amount must be greater than 0")
    @NotNull(message = "Amount must not be null")
    private Double amount;

}
