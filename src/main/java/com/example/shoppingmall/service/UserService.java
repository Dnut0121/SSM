package com.example.shoppingmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shoppingmall.dto.Purchase;
import com.example.shoppingmall.dto.User;
import com.example.shoppingmall.repository.PurchaseRepository;
import com.example.shoppingmall.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private  PurchaseRepository purchaseRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    @Transactional 
    public void registerUser(User user) {
        user.setPw(passwordEncoder.encode(user.getPw())); // 비밀번호 암호화
        userRepository.save(user); // DB에 저장
    }

    // 로그인 검증
    public boolean loginUser(String id, String rawPassword) {
        return userRepository.findById(id)
                .map(user -> passwordEncoder.matches(rawPassword, user.getPw())) // 비밀번호 검증
                .orElse(false);
    }
    // 사용자 정보 가져오기
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null); // 사용자 ID로 조회
    }

    // 사용자 정보 수정
    public void updateUser(User user) {
        userRepository.save(user); // 수정된 사용자 정보 저장
    }
    
    public List<Purchase> getPurchasesByUser(User user) {
        return purchaseRepository.findByUser(user);
    }
}
