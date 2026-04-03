package com.store.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// import com.store.store.dtos.product.ProductDto;
import com.store.store.entities.Product;

public interface ProductRepo extends JpaRepository <Product,Long> {
    public List<Product> findTop8ByOrderByTotalSoldDesc();
    public List<Product> findByCategory_CategoryName(String categoryName);
}
