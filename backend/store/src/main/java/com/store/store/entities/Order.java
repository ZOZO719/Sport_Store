package com.store.store.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
// import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
// import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "customerName" , nullable = false)
    private String customerName;

    @Column(name = "phoneNumber" , nullable = false)
    private String phoneNumber;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "adressLine" , nullable = false)
    private String adressLine;

    @Column(name = "city" , nullable = false)
    private String city;

    @Column(name = "country" , nullable = false)
    private String country;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @Positive
    @Column(name = "totalPrice")
    private BigDecimal totalPrice ;

    @Builder.Default
    @Column(name = "delivaryEstimatedDays")
    private int delivaryEstimatedDays = 3;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    
    
}
