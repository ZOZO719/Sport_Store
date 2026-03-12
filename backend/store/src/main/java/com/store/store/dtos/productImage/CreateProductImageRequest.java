package com.store.store.dtos.productImage;

import lombok.Data;

@Data
public class CreateProductImageRequest {
     private String url;
    private Long productId;
}
