package com.store.store.serviceImplementation.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.store.dtos.product.CreateProductRequest;
import com.store.store.dtos.product.ProductDto;
import com.store.store.dtos.product.UpdateProductRequest;
import com.store.store.entities.Category;
import com.store.store.entities.Product;
import com.store.store.mappers.product.ProductMapper;
import com.store.store.repository.CategoryRepo;
import com.store.store.repository.ProductRepo;
import com.store.store.service.productser.ProductService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProductSerimpl implements ProductService{
     private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final ProductMapper productMapper;

    @Override
    public ProductDto createProduct(CreateProductRequest dto) {

        Product product = productMapper.toEntity(dto);

        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategory(category);

        Product saved = productRepo.save(product);

        return productMapper.toDto(saved);
    }

    @Override
    public List<ProductDto> getAllProducts() {

        return productRepo.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto getProductById(Long id) {

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return productMapper.toDto(product);
    }

    @Override
    public ProductDto updateProduct(Long id, UpdateProductRequest dto) {

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productMapper.updateProduct(dto, product);

        if (dto.getCategoryId() != null) {

            Category category = categoryRepo.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            product.setCategory(category);
        }

        Product updated = productRepo.save(product);

        return productMapper.toDto(updated);
    }

    @Override
    public void deleteProduct(Long id) {

        if (!productRepo.existsById(id)) {
            throw new RuntimeException("Product not found");
        }

        productRepo.deleteById(id);
    }
}
