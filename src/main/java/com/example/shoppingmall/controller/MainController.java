package com.example.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	 @GetMapping("/signup")
	    public String signupPage() {
	    	return "signup";
	    }
	
	 @GetMapping("/login")
	    public String loginPage() {
	    	return "login";
	    }
	 
	 @GetMapping("/")
	    public String indexPage() {
	        return "index"; 
	    }

	    
}
