package com.laze.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laze.blog.dto.ResponseDto;
import com.laze.blog.model.User;

@RestController
public class UserApiController {

	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("api/user save 호출됨");
		return new ResponseDto<Integer>(HttpStatus.OK,1); //자바 오브젝트를 JSON으로 변환해서 리턴
	}
}
