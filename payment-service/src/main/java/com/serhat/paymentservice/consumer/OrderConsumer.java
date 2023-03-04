package com.serhat.paymentservice.consumer;

import com.serhat.paymentservice.constant.PaymentStatus;
import com.serhat.paymentservice.model.entity.PaymentEntity;
import com.serhat.paymentservice.model.event.OrderPlacedEvent;
import com.serhat.paymentservice.model.event.PaymentEvent;
import com.serhat.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderConsumer {

    private final PaymentRepository paymentRepository;
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @KafkaListener(topics = "order-events")
    public void consume(OrderPlacedEvent orderPlacedEvent) {
        log.info("OrderPlacedEvent consumed: {}", orderPlacedEvent);
        log.debug("Creating PaymentEntity" + orderPlacedEvent);
        PaymentEntity paymentEntity = PaymentEntity.builder()
                .orderId(orderPlacedEvent.getOrderId())
                .customerId(orderPlacedEvent.getCustomerId())
                .amount(orderPlacedEvent.getTotalAmount().doubleValue())
                .paymentStatus(PaymentStatus.SUCCESS)
                .build();
        log.info("PaymentEntity created: {}", paymentEntity);
        log.debug("Saving paymentEntity to database");
        var payment = paymentRepository.save(paymentEntity);

        log.debug("PaymentEntity saved to database: {}", payment);
        sendPaymentEvent(PaymentEvent.builder()
                .paymentId(payment.getId())
                .orderId(payment.getOrderId())
                .customerId(payment.getCustomerId())
                .amount(payment.getAmount())
                .build());
    }

    public void sendPaymentEvent(PaymentEvent paymentEvent) {
        log.debug("Sending payment event to kafka");
        kafkaTemplate.send("payment-events", paymentEvent);
        log.debug("Payment event sent to kafka");
    }

}
