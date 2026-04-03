package com.store.store.mappers;

import org.mapstruct.Mapper;

import com.store.store.dtos.HeroBanner.HeroBannerDto;
import com.store.store.entities.HeroBanner;

@Mapper(componentModel = "spring")
public interface HeroMapper {
    HeroBannerDto toDto(HeroBanner heroBanner);
    
}
