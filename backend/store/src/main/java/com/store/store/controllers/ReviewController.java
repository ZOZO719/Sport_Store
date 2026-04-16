package com.store.store.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.store.dtos.Review.CreateReviewRequest;
import com.store.store.dtos.Review.ReviewDto;
import com.store.store.service.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // GET /api/products/{id}/reviews
    // PUBLIC — أي شخص يقدر يقرأ الـ reviews
    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewDto>> getProductReviews(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getProductReviews(id));
    }

    // POST /api/products/{id}/reviews
    // USER — بس المسجلين يقدروا يكتبوا review
    // هلق بنمرر userId كـ param — لاحقاً بنجيبه من الـ JWT تلقائياً
    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewDto> addReview(
            @PathVariable Long id,
            @RequestParam Long userId,        // مؤقت — رح يتغير لما نضيف JWT
            @Valid @RequestBody CreateReviewRequest request) {
        return ResponseEntity.ok(reviewService.addReview(id, userId, request));
    }
}