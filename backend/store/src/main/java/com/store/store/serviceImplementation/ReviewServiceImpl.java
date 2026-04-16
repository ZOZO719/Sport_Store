package com.store.store.serviceImplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.store.store.dtos.Review.CreateReviewRequest;
import com.store.store.dtos.Review.ReviewDto;
import com.store.store.entities.Product;
import com.store.store.entities.Review;
import com.store.store.entities.User;
import com.store.store.mappers.ReviewMapper;
import com.store.store.repository.ProductRepo;
import com.store.store.repository.ReviewRepo;
import com.store.store.repository.UserRepo;
import com.store.store.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> getProductReviews(Long productId) {

        // تحقق إن المنتج موجود أول
        if (!productRepo.existsById(productId))
            throw new RuntimeException("Product not found");

        // جيب كل reviews المنتج وحولها لـ DTO
        return reviewRepo.findByProduct_Id(productId)
                .stream()
                .map(reviewMapper::toDto)
                .toList();
    }

    @Override
    public ReviewDto addReview(Long productId, Long userId, CreateReviewRequest request) {

        // تحقق إن المنتج موجود
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // تحقق إن الـ user موجود
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // تحقق إن الـ user ما كتب review على هاد المنتج قبل
        // مش منطقي يكتب review مرتين على نفس المنتج
        if (reviewRepo.findByProduct_IdAndUser_Id(productId, userId).isPresent())
            throw new RuntimeException("You already reviewed this product");

        // بناء الـ Review entity
        Review review = new Review();
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setProduct(product);
        review.setUser(user);

        return reviewMapper.toDto(reviewRepo.save(review));
    }
}