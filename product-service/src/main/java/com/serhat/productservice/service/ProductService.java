package com.serhat.productservice.service;

import com.serhat.productservice.model.dto.request.ProductAddRequest;
import com.serhat.productservice.model.dto.response.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductDto> getProducts();

    ProductDto getProductById(Long id);

    List<ProductDto> searchProducts(String category, BigDecimal minPrice, BigDecimal maxPrice);

    ProductDto createProduct(ProductAddRequest productAddRequest);

    List<ProductDto> filterProducts(Boolean availability, String color, String brand);

    ProductDto updateProduct(Long id, ProductAddRequest productAddRequest);

    void deleteProduct(Long id);
}
