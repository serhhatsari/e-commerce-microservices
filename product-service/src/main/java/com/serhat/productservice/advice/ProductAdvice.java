package com.serhat.productservice.advice;


import com.serhat.productservice.controller.ProductController;
import com.serhat.productservice.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice(assignableTypes = ProductController.class)
@Slf4j
public class ProductAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Error> handleProductNotFoundException(ProductNotFoundException err) {
        log.error("Product not found exception is handled: " +  err.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Error.builder().message(err.getMessage()).timestamp(LocalDateTime.now()).build());
    }

}
