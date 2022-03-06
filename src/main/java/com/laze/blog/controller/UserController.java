package com.laze.blog.controller;

import java.util.UUID;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laze.blog.model.KakaoProfile;
import com.laze.blog.model.OAuthToken;
import com.laze.blog.model.User;
import com.laze.blog.service.UserService;

@Controller
public class UserController {
	
	@Value("${laze.key}")
	private String lazeKey;
	
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
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
	
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {
		
		//POST 방식으로 key=value 데이터를 요청 (카카오쪽으로)
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "bdc11d8e1ae4c53a58109a1a6218d8f7");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params,headers);
		
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oAuthToken = null;
		try {
			 oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("카카오 액세스 토큰"+ oAuthToken.getAccess_token());
		
		RestTemplate rt2 = new RestTemplate();
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		
		
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
				new HttpEntity<>(headers2);
		
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com//v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest,
				String.class);
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			 kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("카카오 아이디(번호) :" +kakaoProfile.getId());
		System.out.println("카카오 이메일 :" + kakaoProfile.getKakao_account().getEmail());
		
		
		
		User kakaoUser = User.builder()
				.userName(kakaoProfile.getId().toString())
				.password(lazeKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("Kakao")
				.build();
		
		User originalUser = userService.checkMember(kakaoUser.getUserName());
		if(originalUser.getUserName() == null) {
		System.out.println("기존회원이 아닙니다");
		userService.register(kakaoUser);
		}
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUserName(), lazeKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	
		
		return "redirect:/";
	
	}
}

