package com.store.store.dtos.order;

import java.math.BigDecimal;

// import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateOrderRequest {
    
     private Long userId;

    private String customerName;
    private String phoneNumber;
    private String email;
    private String adressLine;
    private String city;
    private String country;

    private BigDecimal totalPrice;
    private int delivaryEstimatedDays;
}