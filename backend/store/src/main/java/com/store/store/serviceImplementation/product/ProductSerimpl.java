package com.store.store.serviceImplementation.product;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.store.store.dtos.product.CreateProductRequest;
import com.store.store.dtos.product.ProductDetailsDto;
import com.store.store.dtos.product.ProductDto;
import com.store.store.dtos.product.UpdateProductRequest;
import com.store.store.entities.Category;
import com.store.store.entities.Product;
import com.store.store.mappers.product.ProductMapper;
import com.store.store.repository.CategoryRepo;
import com.store.store.repository.ProductRepo;
import com.store.store.service.productser.ProductService;
import com.store.store.specifications.ProductSpecification;

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
    public ProductDetailsDto getProductById(Long id) {
Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toDetailDto(product);
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

    @Override
    public Page<ProductDto> getProductByFilter(String category, String itemType, int page, int size, String sort,
            BigDecimal minPrice, BigDecimal maxPrice, String brand) {
           
        Pageable pageable = PageRequest.of(page,size, Sort.by(sort));

        Page<Product> products = productRepo.findAll(ProductSpecification.withFilters(category, itemType, minPrice, maxPrice, brand),pageable);

        return products.map(productMapper::toDto); 
    }

    
}
