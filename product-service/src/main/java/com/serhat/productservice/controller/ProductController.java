package com.serhat.productservice.controller;

import com.serhat.productservice.model.dto.ProductDto;
import com.serhat.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProducts(@RequestParam(value = "name", required = false) String name,
                                                        @RequestParam(value = "category", required = false) String category,
                                                        @RequestParam(value = "price-range", required = false) String priceRange) {

        return new ResponseEntity<>(productService.searchProducts(name, category, priceRange), HttpStatus.OK);

    }



}


