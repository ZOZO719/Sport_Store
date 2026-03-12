package com.store.store.dtos.product;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class UpdateProductRequest {
     private String productName;

    private String productDesc;

    private BigDecimal productPrice;

    private int productQunatity;

    private int discountPercent;

    private boolean isActive;

    private Long categoryId;
}
