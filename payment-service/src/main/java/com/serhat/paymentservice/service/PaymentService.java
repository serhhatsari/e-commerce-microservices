package com.serhat.paymentservice.service;

import com.serhat.paymentservice.entity.dto.request.PaymentRequest;
import com.serhat.paymentservice.entity.dto.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest paymentRequest);

    PaymentResponse getPayment(Integer id);

    PaymentResponse updatePayment(Integer id, PaymentRequest paymentRequest);

    void deletePayment(Integer id);
}
