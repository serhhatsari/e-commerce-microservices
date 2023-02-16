package com.serhat.orderservice.repository;

import com.serhat.orderservice.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<OrderEntity, Long> {
}
