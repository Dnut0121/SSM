package com.example.shoppingmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shoppingmall.dto.shoes;

public interface ShoesRepository extends JpaRepository<shoes, String> {
}
