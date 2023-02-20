package com.serhat.paymentservice.controller;

import com.serhat.paymentservice.model.dto.request.PaymentRequest;
import com.serhat.paymentservice.model.dto.response.PaymentResponse;
import com.serhat.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
      private final PaymentService paymentService;

        @PostMapping
        public ResponseEntity<PaymentResponse> createPayment(@RequestBody @Valid PaymentRequest paymentRequest) {
            log.info("Payment request received: {}", paymentRequest);
            return new ResponseEntity<>(paymentService.createPayment(paymentRequest), HttpStatus.CREATED);
        }

        @GetMapping("/{id}")
        public ResponseEntity<PaymentResponse> getPayment(@PathVariable("id") Long id) {
            return new ResponseEntity<>( paymentService.getPayment(id), HttpStatus.OK);
        }

        @GetMapping("/customer/{id}")
        public ResponseEntity<List<PaymentResponse>> getPaymentsByCustomerId(@PathVariable("id") Long id) {
            return new ResponseEntity<>( paymentService.getPaymentsByCustomerId(id), HttpStatus.OK);
        }

        @PutMapping("/{id}")
        public ResponseEntity<PaymentResponse> updatePayment(@PathVariable("id") Long id, @Valid @RequestBody PaymentRequest paymentRequest) {
            return new ResponseEntity<>(paymentService.updatePayment(id, paymentRequest), HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deletePayment(@PathVariable("id") Long id) {
            paymentService.deletePayment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }


}
