package com.serhat.paymentservice.repository;

import com.serhat.paymentservice.model.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public  interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    List<PaymentEntity> getPaymentEntitiesByCustomerId(Long id);
}
