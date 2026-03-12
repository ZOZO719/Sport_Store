package com.store.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.store.store.entities.Category;

public interface CategoryRepo extends JpaRepository <Category,Long>{
}
