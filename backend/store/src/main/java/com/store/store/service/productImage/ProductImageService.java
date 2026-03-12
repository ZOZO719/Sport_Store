package com.store.store.service.productImage;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.store.dtos.productImage.CreateProductImageRequest;
import com.store.store.dtos.productImage.ProductImageDto;
import com.store.store.dtos.productImage.UpdateProductImageRequest;

@Service
public interface ProductImageService {
     ProductImageDto createProductImage(CreateProductImageRequest dto);

    List<ProductImageDto> getAll();

    ProductImageDto getById(Long id);

    ProductImageDto update(Long id, UpdateProductImageRequest dto);

    void delete(Long id);
}
