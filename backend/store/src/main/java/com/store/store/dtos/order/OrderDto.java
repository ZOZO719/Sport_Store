package com.store.store.dtos.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrderDto {
   
    private Long id;

    private Long userId;

    private String customerName;
    private String phoneNumber;
    private String email;
    private String adressLine;
    private String city;
    private String country;

    private String orderStatus;

    private BigDecimal totalPrice;

    private int delivaryEstimatedDays;

    private LocalDateTime createdAt;
}
