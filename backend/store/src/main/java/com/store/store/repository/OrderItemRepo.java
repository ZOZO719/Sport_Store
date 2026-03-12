package com.store.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.store.entities.OrderItem;

public interface OrderItemRepo extends JpaRepository <OrderItem,Long> {
    
}
