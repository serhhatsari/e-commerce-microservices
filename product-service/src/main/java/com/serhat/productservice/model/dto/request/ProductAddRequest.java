package com.serhat.productservice.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddRequest {


    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;


    private BigDecimal price;

    @NotBlank(message = "Category cannot be blank")
    private String category;


    private Integer stock;

    private Boolean availability;

    @NotBlank(message = "Color cannot be blank")
    private String color;

    @NotBlank(message = "Brand cannot be blank")
    private String brand;
}