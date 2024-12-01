package com.example.shoppingmall.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shoppingmall.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(String id); // 아이디로 사용자 조회
    
}