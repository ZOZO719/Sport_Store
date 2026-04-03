package com.store.store.dtos.product;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AllProductDto {
    private Long id;

    private String productName;

    private String productDesc;

    private BigDecimal productPrice;

    private int productQunatity;
}
