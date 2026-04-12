package com.store.store.mappers.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.store.store.dtos.product.CreateProductRequest;
import com.store.store.dtos.product.ProductDetailsDto;
import com.store.store.dtos.product.ProductDto;
import com.store.store.dtos.product.UpdateProductRequest;
import com.store.store.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.categoryName", target = "categoryName")
    @Mapping(target = "mainImage", ignore = true)
    ProductDto toDto(Product product);

     @Mapping(source = "category.categoryName", target = "categoryName")
    ProductDetailsDto toDetailDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "totalSold", ignore = true) 
    Product toEntity(CreateProductRequest dto);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "totalSold", ignore = true) 
    void updateProduct(UpdateProductRequest dto, @MappingTarget Product product);
    
}
