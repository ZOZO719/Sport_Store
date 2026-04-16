package com.store.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.store.entities.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    // جيب كل reviews المنتج — بنستخدمها بالـ GET
    List<Review> findByProduct_Id(Long productId);

    // تحقق إذا الـ user كتب review على هاد المنتج قبل
    // Optional لأنه ممكن ما يكون كتب review
    Optional<Review> findByProduct_IdAndUser_Id(Long productId, Long userId);
}