package com.store.store.service;

import java.util.List;

import com.store.store.dtos.Review.CreateReviewRequest;
import com.store.store.dtos.Review.ReviewDto;

public interface ReviewService {
        // جيب كل reviews منتج معين
    List<ReviewDto> getProductReviews(Long productId);

    // أضف review جديدة — userId بنجيبه من الـ JWT لاحقاً
    // هلق بنمرره manually
    ReviewDto addReview(Long productId, Long userId, CreateReviewRequest request);
}
