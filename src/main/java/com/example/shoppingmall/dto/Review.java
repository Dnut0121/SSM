package com.example.shoppingmall.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rSeq; // 리뷰 ID

    @Column(nullable = false)
    private String productId; // 리뷰 대상 상품 ID

    @Column(nullable = false)
    private String content; // 리뷰 내용

    @Column(nullable = false)
    private int rating; // 별점

    @Column(nullable = false)
    private String username; // 작성자 ID
}
