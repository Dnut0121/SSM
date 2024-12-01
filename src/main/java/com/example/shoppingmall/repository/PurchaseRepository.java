package com.example.shoppingmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppingmall.dto.Purchase;
import com.example.shoppingmall.dto.User;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	List<Purchase> findByUser(User user);
}