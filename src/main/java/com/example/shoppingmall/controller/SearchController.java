package com.example.shoppingmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.shoppingmall.dto.Shoes;
import com.example.shoppingmall.repository.ShoesRepository;

@Controller
public class SearchController {

    @Autowired
    private ShoesRepository shoesRepository;

 // 검색 처리
    @GetMapping("/search")
    @ResponseBody
    public Map<String, Object> search(@RequestParam("query") String query) {
        List<Shoes> shoesList;

        if (query == null || query.isEmpty()) {
            // 검색어가 비어있으면 모든 상품을 반환
            shoesList = shoesRepository.findAll();
        } else {
            // 검색어가 있을 경우 'name' 필드에 포함된 제품을 검색
            shoesList = shoesRepository.findByNameContainingIgnoreCase(query);
        }

        // 상품 목록을 JSON 형식으로 반환
        Map<String, Object> response = new HashMap<>();
        response.put("productList", shoesList);
        return response;
    }



}