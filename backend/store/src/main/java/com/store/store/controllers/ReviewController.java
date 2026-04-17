package com.store.store.controllers;

import java.security.Principal;
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
import com.store.store.entities.User;
import com.store.store.repository.UserRepo;
import com.store.store.service.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserRepo userRepo;

    // GET /api/products/{id}/reviews
    // PUBLIC — أي شخص يقدر يقرأ الـ reviews
    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewDto>> getProductReviews(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getProductReviews(id));
    }

   // ✅ بدل @RequestParam Long userId — نجيب الـ user من الـ JWT
@PostMapping("/{id}/reviews")
public ResponseEntity<ReviewDto> addReview(
        @PathVariable Long id,
        @Valid @RequestBody CreateReviewRequest request,
        // ✅ Principal بيجيب الـ authenticated user تلقائياً من الـ SecurityContext
        // اللي حطه الـ JwtAuthFilter
        Principal principal) {

    // principal.getName() بيرجع الـ email اللي حطيناه كـ subject بالـ JWT
    String email = principal.getName();

    User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    return ResponseEntity.ok(reviewService.addReview(id, user.getId(), request));
}
}