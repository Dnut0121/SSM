package com.example.shoppingmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.shoppingmall.dto.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(String productId); // 특정 상품의 리뷰 목록 조회
}
