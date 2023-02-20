package com.serhat.paymentservice.service.impl;

import com.serhat.paymentservice.model.dto.request.PaymentRequest;
import com.serhat.paymentservice.model.dto.response.PaymentResponse;
import com.serhat.paymentservice.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        return null;
    }

    @Override
    public PaymentResponse getPayment(Integer id) {
        return null;
    }

    @Override
    public PaymentResponse updatePayment(Integer id, PaymentRequest paymentRequest) {
        return null;
    }

    @Override
    public void deletePayment(Integer id) {

    }
}
