package com.laze.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laze.blog.model.User;
import com.laze.blog.repository.UserRepository;
// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌. IoC
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional // 전체가 성공하면 성공, 하나라도 실패하면 전체가 롤백
	public void register(User user) {
		
			userRepository.save(user);
			
	}
}
