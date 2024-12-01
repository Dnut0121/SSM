package com.example.shoppingmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingmall.dto.User;
import com.example.shoppingmall.repository.ShoesRepository;
import com.example.shoppingmall.service.UserService;

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
    
    // 마이페이지로 이동
    @GetMapping("/mypage")
    public String myPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserId = authentication.getName(); // 로그인한 사용자 ID

        User user = userService.getUserById(loggedInUserId);
        
        if (user != null) {
            model.addAttribute("user", user); // 사용자 정보를 모델에 추가
        } else {
            model.addAttribute("error", "사용자 정보를 찾을 수 없습니다.");
        }

        return "myPage"; // myPage.html 뷰 반환
    }
    
 // 사용자 정보 수정
    @PostMapping("/update-user")
    public String updateUser(@RequestParam("size") int size, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserId = authentication.getName(); // 로그인한 사용자 ID

        // 사용자 정보 수정
        User user = userService.getUserById(loggedInUserId);
        
        if (user != null) {
            user.setSize(size); // 사이즈 수정
            userService.updateUser(user); // 수정된 정보 저장
            model.addAttribute("user", user);
            model.addAttribute("message", "정보가 수정되었습니다.");
        } else {
            model.addAttribute("error", "사용자 정보를 찾을 수 없습니다.");
        }

        return "myPage"; // 수정 후 다시 마이페이지로 리디렉션
    }
    

    
}
