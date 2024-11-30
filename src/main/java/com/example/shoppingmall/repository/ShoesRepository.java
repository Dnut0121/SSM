package com.example.shoppingmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shoppingmall.dto.Shoes;

public interface ShoesRepository extends JpaRepository<Shoes, String> {
	
    // 대소문자 구분 없이 이름에 검색어가 포함된 제품을 찾는 메서드
    List<Shoes> findByNameContainingIgnoreCase(String name);
}
