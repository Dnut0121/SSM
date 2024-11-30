package com.example.shoppingmall.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shoppingmall.dto.User;
import com.example.shoppingmall.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
}
