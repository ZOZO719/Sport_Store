package com.store.store.dtos.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.store.store.dtos.productImage.ProductImageDto;

import lombok.Data;

@Data
public class ProductDetailsDto {
     private Long id;
    private String productName;
    private String productDesc;
    private BigDecimal productPrice;
    private BigDecimal discountPrice;
    private int productQuantity;
    private String itemType;
    private String brand;
    private List<String> sizes;
    private String categoryName;
    private List<ProductImageDto> images;  // كل الصور
    private LocalDateTime createdAt;
}
