package com.example.shoppingmall.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shoppingmall.dto.Shoes;

public interface ShoesRepository extends JpaRepository<Shoes, String> {
	// 특정 ID로 상품 조회 (기본 메서드로 지원됨)
	Optional<Shoes> findById(String id);

    // 대소문자 구분 없이 이름에 검색어가 포함된 제품을 찾는 메서드
    List<Shoes> findByNameContainingIgnoreCase(String name);
}
