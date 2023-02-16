package com.serhat.productservice.model.dto.response;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String category;

    private BigDecimal price;

    private Integer stock;

    private Boolean availability;

    private String color;

    private String brand;
}