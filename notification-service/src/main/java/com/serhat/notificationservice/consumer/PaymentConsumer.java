package com.serhat.notificationservice.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentConsumer {
    @KafkaListener(topics = "payment-events")
    public void consume() {
        log.info("Payment event consumed");
    }

}
