package com.example.shoppingmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingmall.dto.Shoes;
import com.example.shoppingmall.repository.ShoesRepository;

@Controller
public class ProductDetailController {

    @Autowired
    private ShoesRepository shoesRepository;

    // 상품 상세 페이지로 이동
    @GetMapping("/product-detail")
    public String productDetail(@RequestParam("id") String productId, Model model) {
        // 상품 정보 조회
        Shoes product = shoesRepository.findById(productId).orElse(null);

        // 조회된 상품 정보를 모델에 추가
        if (product != null) {
            model.addAttribute("product", product);
        } else {
            model.addAttribute("error", "상품 정보를 찾을 수 없습니다.");
        }

        return "detail"; // detail.html 뷰 반환
    }
}
