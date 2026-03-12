package com.store.store.mappers.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.store.store.dtos.product.CreateProductRequest;
import com.store.store.dtos.product.ProductDto;
import com.store.store.dtos.product.UpdateProductRequest;
import com.store.store.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    Product toEntity(CreateProductRequest dto);

    @Mapping(target = "category", ignore = true)
    void updateProduct(UpdateProductRequest dto, @MappingTarget Product product);
    
}
