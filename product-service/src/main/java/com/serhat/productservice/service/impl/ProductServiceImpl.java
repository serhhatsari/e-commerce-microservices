package com.serhat.productservice.service.impl;


import com.serhat.productservice.exception.ProductNotFoundException;
import com.serhat.productservice.model.converter.ProductConverter;
import com.serhat.productservice.model.dto.request.ProductAddRequest;
import com.serhat.productservice.model.dto.response.ProductDto;
import com.serhat.productservice.repository.ProductRepository;
import com.serhat.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final WebClient webClient;

    @Override
    @Cacheable(cacheNames = "products")
    public List<ProductDto> getProducts() {
        log.debug("getProducts() is called");
        return productRepository.findAll().stream()
                .map(ProductConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
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
    @CachePut(cacheNames = "products", key = "#id", unless = "#result == null")
    @CacheEvict(cacheNames = "products", allEntries = true)
    public ProductDto updateProduct(Long id, ProductAddRequest productAddRequest) {
        log.debug("updateProduct() is called");
        return productRepository.findById(id)
                .map(product -> {
                    product.setCategory(productAddRequest.getCategory());
                    product.setPrice(productAddRequest.getPrice());
                    product.setBrand(productAddRequest.getBrand());
                    product.setColor(productAddRequest.getColor());
                    product.setAvailability(productAddRequest.getAvailability());
                    product.setStock(productAddRequest.getStock());
                    product.setDescription(productAddRequest.getDescription());
                    product.setName(productAddRequest.getName());
                   return ProductConverter.convertToDto(productRepository.save(product));
                })
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void deleteProduct(Long id) {
        log.debug("deleteProduct() is called");
        productRepository.deleteById(id);
    }

    @Override
    @Cacheable(cacheNames = "products", key = "#id")
    public ProductDto getProductById(Long id) {
        log.debug("getProductById() is called");
        return productRepository.findById(id)
                .map(ProductConverter::convertToDto)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<ProductDto> searchProducts(String category, BigDecimal minPrice, BigDecimal maxPrice) {
        log.debug("searchProducts() is called");
        log.info("category: {}, minPrice: {}, maxPrice: {}", category, minPrice, maxPrice);
        return productRepository.searchProducts( category, minPrice, maxPrice).stream()
                .map(ProductConverter::convertToDto)
                .collect(Collectors.toList());
    }


}
