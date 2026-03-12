package com.store.store.dtos.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductDto {
     private Long id;

    private String productName;

    private String productDesc;

    private BigDecimal productPrice;

    private int productQunatity;

    private int discountPercent;

    private boolean isActive;

    private Long categoryId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedTime;
}
