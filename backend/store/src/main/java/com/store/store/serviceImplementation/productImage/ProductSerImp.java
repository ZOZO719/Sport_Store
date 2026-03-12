package com.store.store.serviceImplementation.productImage;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.store.store.dtos.productImage.CreateProductImageRequest;
import com.store.store.dtos.productImage.ProductImageDto;
import com.store.store.dtos.productImage.UpdateProductImageRequest;
import com.store.store.entities.Product;
import com.store.store.entities.ProductImage;
import com.store.store.mappers.productImage.ProductImageMapper;
import com.store.store.repository.ProductImageRepo;
import com.store.store.repository.ProductRepo;
import com.store.store.service.productImage.ProductImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductSerImp implements ProductImageService {
    
    private final ProductImageRepo imageRepo;
    private final ProductRepo productRepo;
    private final ProductImageMapper mapper;

    @Override
    public ProductImageDto createProductImage(CreateProductImageRequest dto) {

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductImage image = ProductImage.builder()
                .url(dto.getUrl())
                .product(product)
                .createdAt(LocalDateTime.now())
                .build();

        ProductImage saved = imageRepo.save(image);

        return mapper.toDto(saved);
    }

    @Override
    public List<ProductImageDto> getAll() {
        return imageRepo.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ProductImageDto getById(Long id) {

        ProductImage image = imageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        return mapper.toDto(image);
    }

    @Override
    public ProductImageDto update(Long id, UpdateProductImageRequest dto) {

        ProductImage image = imageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        image.setUrl(dto.getUrl());

        ProductImage updated = imageRepo.save(image);

        return mapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {

        ProductImage image = imageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        imageRepo.delete(image);
    }
}
