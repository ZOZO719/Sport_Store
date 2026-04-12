package com.store.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

// import com.store.store.dtos.product.ProductDto;
import com.store.store.entities.Product;

public interface ProductRepo extends JpaRepository <Product,Long>,JpaSpecificationExecutor<Product> {
    public List<Product> findTop8ByOrderByTotalSoldDesc();
}
