package com.serhat.paymentservice.model.converter;

import com.serhat.paymentservice.constant.PaymentStatus;
import com.serhat.paymentservice.model.dto.request.PaymentRequest;
import com.serhat.paymentservice.model.dto.response.PaymentResponse;
import com.serhat.paymentservice.model.entity.PaymentEntity;

public class PaymentConverter {

    public static PaymentEntity toEntity(PaymentRequest request) {
        return PaymentEntity.builder()
                .orderId(request.getOrderId())
                .customerId(request.getCustomerId())
                .amount(request.getAmount())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
    }

    public static PaymentResponse toResponse(PaymentEntity entity) {
        return PaymentResponse.builder()
                .id(entity.getId())
                .orderId(entity.getOrderId())
                .customerId(entity.getCustomerId())
                .amount(entity.getAmount())
                .paymentStatus(entity.getPaymentStatus())
                .build();
    }
}
