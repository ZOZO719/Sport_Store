package com.store.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.store.entities.User;

public interface UserRepo extends JpaRepository <User,Long> {
    
}
