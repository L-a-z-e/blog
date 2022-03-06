package com.laze.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.laze.blog.config.auth.PrincipalDetail;

@Controller
public class UserController {
	
	//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
	// 그냥 주소가 /이면 index.jsp 허용
	//static 이하에 있는 /js/**, /css/**, /image/**
	
	@GetMapping("/auth/registerForm")
	public String registerForm() {
		
		return "user/registerForm";
		
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
		
	}
	
	@GetMapping("/user/updateForm")
		public String userUpdateForm() {
			return "user/updateForm";
		}
	}


