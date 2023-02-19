package com.serhat.productservice.exception;


public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("Product not found!");
    }
}
