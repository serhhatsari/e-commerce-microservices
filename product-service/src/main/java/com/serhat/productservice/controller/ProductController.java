package com.serhat.productservice.controller;

import com.serhat.productservice.model.dto.request.ProductAddRequest;
import com.serhat.productservice.model.dto.response.ProductDto;
import com.serhat.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductAddRequest productAddRequest) {
        return new ResponseEntity<>(productService.createProduct(productAddRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDto>> searchProducts(
                                                        @RequestParam(value = "category", required = false) String category,
                                                        @RequestParam(value = "min-price", required = false) BigDecimal minPrice,
                                                        @RequestParam(value = "max-price", required = false) BigDecimal maxPrice) {
        return new ResponseEntity<>(productService.searchProducts(category, minPrice, maxPrice), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductDto>> filterProducts(@RequestParam(value = "availability", required = false) Boolean availability,
                                                           @RequestParam(value = "color", required = false) String color,
                                                           @RequestParam(value = "brand", required = false) String brand) {

        return new ResponseEntity<>(productService.filterProducts(availability, color, brand), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductAddRequest productAddRequest) {
        return new ResponseEntity<>(productService.updateProduct(id, productAddRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}


