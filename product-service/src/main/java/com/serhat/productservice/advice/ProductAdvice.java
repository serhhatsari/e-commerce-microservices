package com.serhat.productservice.advice;


import com.serhat.productservice.controller.ProductController;
import com.serhat.productservice.exception.ProductNotFoundException;
import com.serhat.productservice.exception.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(assignableTypes = ProductController.class)
@Slf4j
public class ProductAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException err) {
        log.error("Product not found exception is handled: " +  err.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().message(err.getMessage()).timestamp(LocalDateTime.now()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException err) {
        log.error("Method argument not valid exception is handled: " +  err.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().message(err.getMessage()).timestamp(LocalDateTime.now()).build(), HttpStatus.BAD_REQUEST);
    }

}
