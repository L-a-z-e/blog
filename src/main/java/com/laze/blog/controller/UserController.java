package com.laze.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	
	@GetMapping("/registerForm")
	public String registerForm() {
		
		return "user/registerForm";
		
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
		
	}

}
