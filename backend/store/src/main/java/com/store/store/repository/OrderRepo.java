package com.store.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.store.entities.Order;

public interface OrderRepo extends JpaRepository <Order,Long> {
    
}
