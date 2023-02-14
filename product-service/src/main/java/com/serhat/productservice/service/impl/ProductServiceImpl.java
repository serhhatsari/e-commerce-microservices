package com.serhat.productservice.service.impl;


import com.serhat.productservice.model.converter.ProductConverter;
import com.serhat.productservice.model.dto.request.ProductAddRequest;
import com.serhat.productservice.model.dto.response.ProductDto;
import com.serhat.productservice.repository.ProductRepository;
import com.serhat.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getProducts() {
        log.debug("getProducts() is called");
        return productRepository.findAll().stream()
                .map(ProductConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto createProduct(ProductAddRequest productAddRequest) {
        log.debug("createProduct() is called");
        return ProductConverter.convertToDto(productRepository.save(ProductConverter.convertToEntity(productAddRequest)));
    }

    @Override
    public List<ProductDto> filterProducts(Boolean availability, String color, String brand) {
        log.debug("filterProducts() is called");
        return productRepository.filterProducts(availability, color, brand).stream()
                .map(ProductConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long id, ProductAddRequest productAddRequest) {
        log.debug("updateProduct() is called");
        return productRepository.findById(id)
                .map(product -> {
                    product.setCategory(productAddRequest.getCategory());
                    product.setPrice(productAddRequest.getPrice());
                    product.setBrand(productAddRequest.getBrand());
                    product.setColor(productAddRequest.getColor());
                    product.setAvailability(productAddRequest.getAvailability());
                    return ProductConverter.convertToDto(productRepository.save(product));
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public void deleteProduct(Long id) {
        log.debug("deleteProduct() is called");
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto getProductById(Long id) {
        log.debug("getProductById() is called");
        return productRepository.findById(id)
                .map(ProductConverter::convertToDto)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<ProductDto> searchProducts(String category, Integer minPrice, Integer maxPrice) {
        log.debug("searchProducts() is called");
        return productRepository.searchProducts( category, minPrice, maxPrice).stream()
                .map(ProductConverter::convertToDto)
                .collect(Collectors.toList());
    }


}
