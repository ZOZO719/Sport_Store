package com.store.store.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.store.dtos.productImage.CreateProductImageRequest;
import com.store.store.dtos.productImage.ProductImageDto;
import com.store.store.dtos.productImage.UpdateProductImageRequest;
import com.store.store.service.productImage.ProductImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product-images")
@RequiredArgsConstructor
public class ProductImageController {
      private final ProductImageService imageService;

    @PostMapping
    public ResponseEntity<ProductImageDto> create(@RequestBody CreateProductImageRequest dto) {
        return ResponseEntity.ok(imageService.createProductImage(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductImageDto>> getAll() {
        return ResponseEntity.ok(imageService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductImageDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductImageDto> update(
            @PathVariable Long id,
            @RequestBody UpdateProductImageRequest dto) {

        return ResponseEntity.ok(imageService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        imageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
