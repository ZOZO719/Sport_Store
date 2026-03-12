package com.store.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.store.entities.Product;

public interface ProductRepo extends JpaRepository <Product,Long> {
    
}
