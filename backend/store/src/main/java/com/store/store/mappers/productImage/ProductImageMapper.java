package com.store.store.mappers.productImage;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.store.store.dtos.productImage.ProductImageDto;
import com.store.store.entities.ProductImage;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
      @Mapping(source = "product.id", target = "productId")
    ProductImageDto toDto(ProductImage image);
}
