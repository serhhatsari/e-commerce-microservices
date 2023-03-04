package com.serhat.notificationservice.consumer;

import com.serhat.notificationservice.event.PaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentConsumer {
    @KafkaListener(topics = "payment-events")
    public void consume(PaymentEvent paymentEvent) {
        log.info("Payment Event consumed: {}", paymentEvent);
        log.info("Sending notification for payment: {}", paymentEvent.getPaymentId());
        log.info("Super cool notification sent!");
    }

}
