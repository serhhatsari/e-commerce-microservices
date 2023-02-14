package com.serhat.productservice.service;

import com.serhat.productservice.model.dto.request.ProductAddRequest;
import com.serhat.productservice.model.dto.response.ProductDto;

import java.util.List;

public interface ProductService {
    public List<ProductDto> getProducts();

    public ProductDto getProductById(Long id);

    public List<ProductDto> searchProducts(String category, Integer minPrice, Integer maxPrice);

    public ProductDto createProduct(ProductAddRequest productAddRequest);

    public List<ProductDto> filterProducts(Boolean availability, String color, String brand);

    public ProductDto updateProduct(Long id, ProductAddRequest productAddRequest);

    public void deleteProduct(Long id);
}
