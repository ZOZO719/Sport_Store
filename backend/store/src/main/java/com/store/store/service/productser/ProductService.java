package com.store.store.service.productser;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.store.store.dtos.product.CreateProductRequest;
import com.store.store.dtos.product.ProductDetailsDto;
import com.store.store.dtos.product.ProductDto;
import com.store.store.dtos.product.UpdateProductRequest;

@Service
public interface ProductService {
     ProductDto createProduct(CreateProductRequest dto);


     public Page<ProductDto> getProductByFilter( String category, String itemType,
            int page, int size, String sort,
            BigDecimal minPrice, BigDecimal maxPrice, String brand);
    ProductDetailsDto getProductById(Long id);
    

    ProductDto updateProduct(Long id, UpdateProductRequest dto);

    void deleteProduct(Long id);
}
