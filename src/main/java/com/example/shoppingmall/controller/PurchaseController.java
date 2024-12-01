package com.example.shoppingmall.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppingmall.dto.Purchase;
import com.example.shoppingmall.dto.User;
import com.example.shoppingmall.repository.PurchaseRepository;
import com.example.shoppingmall.repository.UserRepository;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;

    public PurchaseController(PurchaseRepository purchaseRepository, UserRepository userRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> purchase(@RequestBody Map<String, String> purchaseData, Authentication authentication) {
        // 로그인된 사용자 ID 가져오기
        String loggedInUserId = authentication.getName();

        // 사용자 정보 조회
        User user = userRepository.findById(loggedInUserId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // 구매 정보 생성 및 저장
        Purchase purchase = new Purchase();
        purchase.setProductName(purchaseData.get("productName"));
        purchase.setSize(purchaseData.get("size"));
        purchase.setColor(purchaseData.get("color"));
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setUser(user);

        purchaseRepository.save(purchase);

        return ResponseEntity.ok("Purchase saved successfully");
    }
}