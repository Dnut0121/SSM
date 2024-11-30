package com.example.shoppingmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingmall.dto.Shoes;
import com.example.shoppingmall.repository.ShoesRepository;

@Controller
public class SearchController {

    @Autowired
    private ShoesRepository shoesRepository;

    // 검색 처리
    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<Shoes> shoesList;

        // 검색어가 비어있으면 모든 신발 목록을 가져옴
        if (query == null || query.isEmpty()) {
            shoesList = shoesRepository.findAll();
        } else {
            // 검색어가 있을 경우 'name' 필드에 포함된 제품을 검색
            shoesList = shoesRepository.findByNameContainingIgnoreCase(query);
        }

        // 검색 결과를 모델에 담아서 view에 전달
        model.addAttribute("productList", shoesList);
        model.addAttribute("searchQuery", query); // 검색어도 함께 전달 (필요시)

        return "index"; // 검색 결과를 'index' 페이지에서 표시
    }
}