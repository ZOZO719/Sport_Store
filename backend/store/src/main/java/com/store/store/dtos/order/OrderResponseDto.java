package com.store.store.dtos.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderResponseDto {
     private Long id;

    private String customerName;
    private String phoneNumber;
    private String email;

    private String adressLine;
    private String city;
    private String country;

    private BigDecimal totalPrice;

    private String orderStatus;

    private int delivaryEstimatedDays;

    private LocalDateTime createdAt;
}
