package com.store.store.dtos.productImage;

import lombok.Data;

@Data
public class ProductImageDto {
    private Long id;
    private String url;
    private Long productId;
}
