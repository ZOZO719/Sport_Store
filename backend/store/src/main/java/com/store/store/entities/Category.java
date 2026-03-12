package com.store.store.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "category")
@Entity
@NoArgsConstructor
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private Long id ; 

    @Column(name = "categoryName")
    private String categoryName;

    
     @CreationTimestamp
    @Column(name = "createdAt",nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();


    
    @CreationTimestamp
    @Column(name = "updatedAt",nullable = false)
    private LocalDateTime updatedTime = LocalDateTime.now();

    
    @OneToMany(mappedBy = "category")
    private List<Product> products;





}

