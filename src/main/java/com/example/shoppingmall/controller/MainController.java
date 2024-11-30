package com.example.shoppingmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.shoppingmall.repository.ShoesRepository;

@Controller
public class MainController {
	
	@Autowired
    private ShoesRepository shoesRepository;
	
	 @GetMapping("/signup")
	    public String signupPage() {
	    	return "signup";
	    }
	
	 @GetMapping("/login")
	    public String loginPage() {
	    	return "login";
	    }
	 
	    @GetMapping("/")
	    public String indexPage(Model model) {
	        // 상품 목록을 모델에 추가
	        model.addAttribute("productList", shoesRepository.findAll());

	        // 로그인 여부 확인
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() 
	                                  && !"anonymousUser".equals(authentication.getPrincipal());
	        model.addAttribute("isAuthenticated", isAuthenticated);

	        if (isAuthenticated) {
	            // 로그인된 사용자 정보 추가
	            String username = authentication.getName();
	            model.addAttribute("username", username);
	        }

	        return "index"; // index.html로 반환
	    }
	
	 @GetMapping("/add-product")
	 public String addProductPage() {
	     return "add-product";
	 }
	 

}

	    

