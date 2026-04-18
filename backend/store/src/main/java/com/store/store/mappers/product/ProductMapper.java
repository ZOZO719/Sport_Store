package com.store.store.mappers.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.store.store.dtos.product.CreateProductRequest;
import com.store.store.dtos.product.ProductDetailsDto;
import com.store.store.dtos.product.ProductDto;
import com.store.store.dtos.product.UpdateProductRequest;
import com.store.store.entities.Product;
import com.store.store.entities.ProductImage;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.categoryName", target = "categoryName")
    // BUG FIX: extract first image URL instead of ignoring mainImage entirely
    @Mapping(source = "images", target = "mainImage", qualifiedByName = "firstImageUrl")
    ProductDto toDto(Product product);

    @Mapping(source = "category.categoryName", target = "categoryName")
    ProductDetailsDto toDetailDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "totalSold", ignore = true)
    @Mapping(target = "discountPrice", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedTime", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    Product toEntity(CreateProductRequest dto);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "totalSold", ignore = true)
    @Mapping(target = "discountPrice", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedTime", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    void updateProduct(UpdateProductRequest dto, @MappingTarget Product product);

    // Helper: pick the URL of the first image, or null if no images
    @Named("firstImageUrl")
    static String firstImageUrl(List<ProductImage> images) {
        if (images == null || images.isEmpty()) return null;
        return images.get(0).getUrl();
    }
}