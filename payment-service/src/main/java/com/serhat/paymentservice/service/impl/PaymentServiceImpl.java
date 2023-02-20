package com.serhat.paymentservice.service.impl;

import com.serhat.paymentservice.exception.PaymentNotFoundException;
import com.serhat.paymentservice.model.converter.PaymentConverter;
import com.serhat.paymentservice.model.dto.request.PaymentRequest;
import com.serhat.paymentservice.model.dto.response.PaymentResponse;
import com.serhat.paymentservice.repository.PaymentRepository;
import com.serhat.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    @CacheEvict(value = "payments", allEntries = true)
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        log.info("Payment request received: {}", paymentRequest);
        return PaymentConverter.toResponse(paymentRepository.save(PaymentConverter.toEntity(paymentRequest)));
    }

    @Override
    @Cacheable(value = "payments", key = "#id")
    public PaymentResponse getPayment(Long id) {
        log.info("Payment get request received: {}", id);
        return PaymentConverter.toResponse(paymentRepository.findById(id).orElseThrow(PaymentNotFoundException::new));
    }

    @Override
    @CachePut(value = "payments", key = "#id")
    public PaymentResponse updatePayment(Long id, PaymentRequest paymentRequest) {
        log.info("Payment update request received: {}", paymentRequest);
        return PaymentConverter.toResponse(
                paymentRepository.findById(id)
                        .map(paymentEntity -> {
                            paymentEntity.setAmount(paymentRequest.getAmount());
                            paymentEntity.setCustomerId(paymentRequest.getCustomerId());
                            paymentEntity.setOrderId(paymentRequest.getOrderId());
                            return paymentRepository.save(paymentEntity);
                        })
                        .orElseThrow(PaymentNotFoundException::new)
        );
    }

    @Override
    @CacheEvict(value = "payments", allEntries = true)
    public void deletePayment(Long id) {
        log.info("Payment delete request received: {}", id);
        paymentRepository.deleteById(id);
    }

    @Override
    public List<PaymentResponse> getPaymentsByCustomerId(Long id) {
        log.info("Payment get by customer id request received: {}", id);
        return paymentRepository.getPaymentEntitiesByCustomerId(id).stream().map(PaymentConverter::toResponse).collect(Collectors.toList());
    }
}
