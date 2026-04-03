package com.store.store.dtos.HeroBanner;

import lombok.Data;

@Data
public class HeroBannerDto {
    private Long id ;
    private String imageUrl;
    private String title;
    private String subtitle;
    private boolean isActive;
}
