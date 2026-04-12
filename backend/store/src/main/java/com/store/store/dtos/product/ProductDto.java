package com.store.store.dtos.product;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDto {
     private Long id;
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal discountPercent;
    private String itemType;
    private Long categoryId;
    private String categoryName;    // اسم الكاتيغوري
    private String mainImage;  
}
