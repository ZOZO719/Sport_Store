package com.store.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.store.entities.ProductImage;

public interface ProductImageRepo extends JpaRepository <ProductImage,Long> {
    
}
