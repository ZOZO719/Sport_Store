package com.store.store.dtos;

import java.util.List;

import com.store.store.dtos.HeroBanner.HeroBannerDto;
import com.store.store.dtos.product.ProductDto;
// import com.store.store.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HomeDto {
    List<HeroBannerDto> heroBannerDtos;
    List<ProductDto> bestSells;
}
