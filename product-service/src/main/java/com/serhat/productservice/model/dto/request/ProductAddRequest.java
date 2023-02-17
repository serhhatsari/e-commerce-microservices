package com.serhat.productservice.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddRequest {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotEmpty(message = "Price cannot be empty")
    private BigDecimal price;

    @NotEmpty(message = "Category cannot be empty")
    private String category;

    @NotEmpty(message = "Stock cannot be empty")
    private Integer stock;

    @NotEmpty(message = "Availability cannot be empty")
    private Boolean availability;

    @NotEmpty(message = "Color cannot be empty")
    private String color;

    @NotEmpty(message = "Brand cannot be empty")
    private String brand;
}