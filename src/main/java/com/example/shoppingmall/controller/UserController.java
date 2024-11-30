package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.User;
import com.example.shoppingmall.repository.ShoesRepository;
import com.example.shoppingmall.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
	
	@Autowired
    private ShoesRepository shoesRepository;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.registerUser(user);
        model.addAttribute("message", "회원가입이 완료되었습니다.");
        return "redirect:/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String loginUser(@RequestParam String id, @RequestParam String pw, Model model) {
        if (userService.loginUser(id, pw)) {
            return "redirect:/index"; // 로그인 성공 시
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login"; // 로그인 실패 시
        }
    }
}
