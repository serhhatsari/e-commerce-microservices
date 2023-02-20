package com.serhat.paymentservice.controller;

import com.serhat.paymentservice.model.dto.request.PaymentRequest;
import com.serhat.paymentservice.model.dto.response.PaymentResponse;
import com.serhat.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
      private final PaymentService paymentService;

        @PostMapping
        public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
            return new ResponseEntity<>(paymentService.createPayment(paymentRequest), HttpStatus.CREATED);
        }

        @GetMapping("/{id}")
        public ResponseEntity<PaymentResponse> getPayment(@PathVariable("id") Integer id) {
            return new ResponseEntity<>( paymentService.getPayment(id), HttpStatus.OK);
        }

        @PutMapping("/{id}")
        public ResponseEntity<PaymentResponse> updatePayment(@PathVariable("id") Integer id, @RequestBody PaymentRequest paymentRequest) {
            return new ResponseEntity<>(paymentService.updatePayment(id, paymentRequest), HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deletePayment(@PathVariable("id") Integer id) {
            paymentService.deletePayment(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }


}
