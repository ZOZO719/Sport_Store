package com.store.store.dtos.orderItem;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreateOrderItemRequest {
    private Long orderId;

    private String productId;

    private String productNameSnapshot;

    private BigDecimal unitPriceSnapshot;

    private Integer quantity;
}
