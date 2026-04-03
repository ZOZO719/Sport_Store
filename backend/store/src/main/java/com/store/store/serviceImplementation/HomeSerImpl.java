package com.store.store.serviceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.store.dtos.HomeDto;
import com.store.store.dtos.HeroBanner.HeroBannerDto;
import com.store.store.dtos.product.ProductDto;
import com.store.store.mappers.HeroMapper;
import com.store.store.mappers.product.ProductMapper;
import com.store.store.repository.HeroRepo;
import com.store.store.repository.ProductRepo;
import com.store.store.service.HomeService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class HomeSerImpl implements HomeService {
   private final HeroRepo heroRepo;
    private final ProductRepo productRepo;
   private final HeroMapper heroMapper;
   private final ProductMapper productMapper;

    @Override
    public HomeDto getHomeData() {
      List<HeroBannerDto> banners = heroRepo.findByIsActiveTrue().stream().map(heroMapper::toDto).toList();
      List<ProductDto> bestSells = productRepo.findTop8ByOrderByTotalSoldDesc().stream().map(productMapper::toDto).toList();

        return new HomeDto(banners,bestSells);
    }
    
}
