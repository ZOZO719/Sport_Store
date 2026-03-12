package com.store.store.service.productser;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.store.dtos.product.CreateProductRequest;
import com.store.store.dtos.product.ProductDto;
import com.store.store.dtos.product.UpdateProductRequest;

@Service
public interface ProductService {
     ProductDto createProduct(CreateProductRequest dto);

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);

    ProductDto updateProduct(Long id, UpdateProductRequest dto);

    void deleteProduct(Long id);
}
