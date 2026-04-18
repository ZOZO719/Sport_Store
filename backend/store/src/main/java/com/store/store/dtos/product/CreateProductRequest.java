package com.store.store.dtos.product;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class CreateProductRequest {
     private String productName;

    private String productDesc;

    private BigDecimal productPrice;

    private int productQuantity;

    // BUG FIX: was int — Product entity uses BigDecimal, MapStruct can't auto-convert
    private BigDecimal discountPercent;

    private boolean isActive;

    private String itemType;
    
    private String brand;

    private List<String> sizes;

    private Long categoryId;

}