package com.serhat.paymentservice.service;

import com.serhat.paymentservice.model.dto.request.PaymentRequest;
import com.serhat.paymentservice.model.dto.response.PaymentResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest paymentRequest);

    PaymentResponse getPayment(Long id);

    PaymentResponse updatePayment(Long id, PaymentRequest paymentRequest);

    void deletePayment(Long id);

    List<PaymentResponse> getPaymentsByCustomerId(Long id);
}
