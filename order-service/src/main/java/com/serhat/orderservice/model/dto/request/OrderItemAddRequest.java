package com.serhat.orderservice.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serhat.orderservice.model.dto.response.OrderDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemAddRequest {

    @NotNull(message = "Product Id cannot be null")
    @Min(value = 1, message = "Product Id must be greater than or equal to 1")
    @JsonProperty("product_id")
    private Long productId;

    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    @NotNull
    private Integer quantity;

    @NotNull
    @Min(value = 1, message = "Price must be greater than or equal to 1")
    private Double price;
}
