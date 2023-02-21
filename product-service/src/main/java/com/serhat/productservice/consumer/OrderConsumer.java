package com.serhat.productservice.consumer;

import com.serhat.productservice.model.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderConsumer {

    @KafkaListener(topics = "order-events")
    public void consume(OrderPlacedEvent orderPlacedEvent) {
        log.info("OrderPlacedEvent consumed: {}", orderPlacedEvent);
    }
}
