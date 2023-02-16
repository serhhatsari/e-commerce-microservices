package com.serhat.productservice.service.impl;

import com.serhat.productservice.model.converter.ProductConverter;
import com.serhat.productservice.model.dto.request.ProductAddRequest;
import com.serhat.productservice.model.dto.response.ProductDto;
import com.serhat.productservice.model.entity.ProductEntity;
import com.serhat.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("Product Service Tests")
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Get Products Test")
    void getProductsTest() {
        // given
        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setId(1L);
        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setId(2L);
        when(productRepository.findAll()).thenReturn(Arrays.asList(productEntity1, productEntity2));

        // when
        List<ProductDto> products = productService.getProducts();

        // then
        verify(productRepository, times(1)).findAll();
        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).getId());
        assertEquals(2L, products.get(1).getId());
    }

    @Test
    @DisplayName("Filter Products Test")
    void filterProductsTest() {
        // given
        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setId(1L);
        productEntity1.setAvailability(true);
        productEntity1.setColor("Red");
        productEntity1.setBrand("Brand 1");

        when(productRepository.filterProducts(true, "Red", "Brand 1")).thenReturn(Collections.singletonList(productEntity1));

        // when
        List<ProductDto> products = productService.filterProducts(true, "Red", "Brand 1");

        // then
        verify(productRepository, times(1)).filterProducts(true, "Red", "Brand 1");
        assertEquals(1, products.size());
        assertEquals(1L, products.get(0).getId());
    }

    @Test
    void updateProductTest() {
        // Arrange
        Long productId = 1L;
        ProductAddRequest updateRequest = new ProductAddRequest();
        updateRequest.setName("Updated product");
        updateRequest.setBrand("Updated brand");
        updateRequest.setPrice(new BigDecimal("99.99"));
        updateRequest.setAvailability(true);

        ProductEntity product = new ProductEntity();
        product.setId(productId);
        product.setName("Product");
        product.setBrand("Brand");
        product.setPrice(new BigDecimal("49.99"));
        product.setAvailability(false);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(ArgumentMatchers.any())).thenReturn(product);

        // Act
        ProductDto updatedProductDto = productService.updateProduct(productId, updateRequest);

        // Assert
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).save(ArgumentMatchers.any(ProductEntity.class));

        assertEquals(updatedProductDto.getId(), productId);
        assertEquals(updatedProductDto.getName(), updateRequest.getName());
        assertEquals(updatedProductDto.getBrand(), updateRequest.getBrand());
        assertEquals(updatedProductDto.getPrice(), updateRequest.getPrice());
        assertEquals(updatedProductDto.getAvailability(), updateRequest.getAvailability());
    }

    @Test
    void updateProductNotFoundTest() {
        // Arrange
        Long productId = 1L;
        ProductAddRequest updateRequest = new ProductAddRequest();

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> productService.updateProduct(productId, updateRequest));
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(0)).save(ArgumentMatchers.any(ProductEntity.class));
    }

    @Test
    public void deleteProductTest() {
        Long id = 1L;

        doNothing().when(productRepository).deleteById(any(Long.class));

        productService.deleteProduct(id);

        verify(productRepository).deleteById(id);
    }
}