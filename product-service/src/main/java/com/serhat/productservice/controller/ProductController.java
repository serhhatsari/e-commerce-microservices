package com.serhat.productservice.controller;

import com.serhat.productservice.model.dto.request.ProductAddRequest;
import com.serhat.productservice.model.dto.response.ProductDto;
import com.serhat.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> getProducts() {
        log.debug("GET /api/products is called");
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductAddRequest productAddRequest) {
        log.debug("POST /api/products is called");
        return new ResponseEntity<>(productService.createProduct(productAddRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        log.debug("GET /api/products/{} is called", id);
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> searchProducts(
                                                        @RequestParam(value = "category", required = false) String category,
                                                        @RequestParam(value = "min-price", required = false) BigDecimal minPrice,
                                                        @RequestParam(value = "max-price", required = false) BigDecimal maxPrice) {
        log.debug("GET /api/products/search is called");
        return new ResponseEntity<>(productService.searchProducts(category, minPrice, maxPrice), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductDto>> filterProducts(@RequestParam(value = "availability", required = false) Boolean availability,
                                                           @RequestParam(value = "color", required = false) String color,
                                                           @RequestParam(value = "brand", required = false) String brand) {
        log.debug("GET /api/products/filter is called");
        return new ResponseEntity<>(productService.filterProducts(availability, color, brand), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductAddRequest productAddRequest) {
        log.debug("PUT /api/products/{} is called", id);
        return new ResponseEntity<>(productService.updateProduct(id, productAddRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("DELETE /api/products/{} is called", id);
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


