package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.Review;
import com.example.shoppingmall.dto.Shoes;
import com.example.shoppingmall.repository.ReviewRepository;
import com.example.shoppingmall.repository.ShoesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductDetailController {

    @Autowired
    private ShoesRepository shoesRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/product-detail")
    public String productDetail(@RequestParam("id") String productId, Model model) {
        Shoes product = shoesRepository.findById(productId).orElse(null);

        if (product != null) {
            model.addAttribute("product", product);
            List<Review> reviews = reviewRepository.findByProductId(productId);
            model.addAttribute("reviews", reviews); // 리뷰 데이터 전달
        } else {
            model.addAttribute("error", "상품 정보를 찾을 수 없습니다.");
        }

        return "detail";
    }

    @PostMapping("/add-review")
    public String addReview(@RequestParam String productId,
                            @RequestParam String content,
                            @RequestParam int rating,
                            @AuthenticationPrincipal org.springframework.security.core.userdetails.User principal) {
        Review review = new Review();
        review.setProductId(productId);
        review.setContent(content);
        review.setRating(rating);
        review.setUsername(principal.getUsername()); // 로그인된 사용자 ID 저장
        reviewRepository.save(review);
        return "redirect:/product-detail?id=" + productId;
    }
    @PostMapping("/delete-review")
    public String deleteReview(@RequestParam("reviewId") Long reviewId, Principal principal, RedirectAttributes redirectAttributes) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null && principal != null && principal.getName().equals(review.getUsername())) {
            reviewRepository.delete(review);
            redirectAttributes.addFlashAttribute("message", "리뷰가 삭제되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "삭제 권한이 없거나 리뷰가 없습니다.");
        }
        return "redirect:/product-detail?id=" + (review != null ? review.getProductId() : "");
    }
    @GetMapping("/product-list")
    @ResponseBody
    public Map<String, Object> productList(@RequestParam(required = false) String category,
                                           @RequestParam(required = false) String query) {
        List<Shoes> productList;

        // 카테고리와 검색어가 있을 경우 둘 다 조건을 적용
        if (category != null && !category.isEmpty() && query != null && !query.isEmpty()) {
            productList = shoesRepository.findByCategoryAndNameContainingIgnoreCase(category, query);
        }
        // 카테고리만 있을 경우
        else if (category != null && !category.isEmpty()) {
            productList = shoesRepository.findByCategory(category);
        }
        // 검색어만 있을 경우
        else if (query != null && !query.isEmpty()) {
            productList = shoesRepository.findByNameContainingIgnoreCase(query);
        }
        // 둘 다 없을 경우 모든 상품
        else {
            productList = shoesRepository.findAll();
        }

        // 응답 데이터 준비
        Map<String, Object> response = new HashMap<>();
        response.put("productList", productList);

        return response;
    }

}
