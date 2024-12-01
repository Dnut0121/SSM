package com.example.shoppingmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    

