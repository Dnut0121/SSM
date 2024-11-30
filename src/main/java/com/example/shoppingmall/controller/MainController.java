package com.example.shoppingmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingmall.dto.shoes;
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
	        model.addAttribute("productList", shoesRepository.findAll());
	        return "index"; 
	    }

	 @GetMapping("/add-product")
	 public String addProductPage() {
	     return "add-product";
	 }
	    
}
