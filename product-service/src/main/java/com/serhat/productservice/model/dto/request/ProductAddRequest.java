package com.serhat.productservice.model.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddRequest {
    private String name;

    private String description;

    private BigDecimal price;

    private String category;
    
    private Integer stock;

    private Boolean availability;

    private String color;

    private String brand;
}