package com.serhat.productservice.model.converter;

import com.serhat.productservice.model.dto.request.ProductAddRequest;
import com.serhat.productservice.model.dto.response.ProductDto;
import com.serhat.productservice.model.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
public class ProductConverter {

    public static ProductDto convertToDto(ProductEntity productEntity) {
        log.debug("convertToDto() is called");
        return ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .category(productEntity.getCategory())
                .availability(productEntity.getAvailability())
                .color(productEntity.getColor())
                .brand(productEntity.getBrand())
                .build();
    }

    public static ProductEntity convertToEntity(ProductAddRequest productAddRequest) {
        log.debug("convertToEntity() is called");
        return ProductEntity.builder()
                .name(productAddRequest.getName())
                .description(productAddRequest.getDescription())
                .price(productAddRequest.getPrice())
                .stock(productAddRequest.getStock())
                .category(productAddRequest.getCategory())
                .availability(productAddRequest.getAvailability())
                .color(productAddRequest.getColor())
                .brand(productAddRequest.getBrand())
                .build();
    }

}
