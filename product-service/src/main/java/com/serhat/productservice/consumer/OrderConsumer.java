package com.serhat.productservice.consumer;

import com.serhat.productservice.exception.ProductNotFoundException;
import com.serhat.productservice.model.event.OrderPlacedEvent;
import com.serhat.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderConsumer {

    private final ProductRepository productRepository;

    @KafkaListener(topics = "order-events")
    public void consume(OrderPlacedEvent orderPlacedEvent) {
        log.debug("OrderPlacedEvent consumed: {}", orderPlacedEvent);
        var orderItems = orderPlacedEvent.getOrderItems();
        orderItems.forEach(orderItem -> {
            log.debug("OrderItem consumed: {}", orderItem);
            var product = productRepository.findById(orderItem.getProductId()).orElseThrow(ProductNotFoundException::new);
            product.setStock(product.getStock() - orderItem.getQuantity());
            productRepository.save(product);
        });
        log.info("OrderPlacedEvent consumed successfully: {}", orderPlacedEvent);
    }
}
