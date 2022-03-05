package com.laze.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laze.blog.dto.ResponseDto;
import com.laze.blog.model.User;
import com.laze.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	


	@PostMapping("/auth/registerForm")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("/auth/registerForm save 호출됨");
		userService.register(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); //자바 오브젝트를 JSON으로 변환해서 리턴
	}
	
	@PutMapping("/api/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.update(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	

}
