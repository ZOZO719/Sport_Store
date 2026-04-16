package com.store.store.dtos.Review;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewDto {
    private Long id;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
    private String userName;
}
