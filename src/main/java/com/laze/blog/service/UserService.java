package com.laze.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laze.blog.model.User;
import com.laze.blog.repository.UserRepository;
// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌. IoC
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional// 전체가 성공하면 성공, 하나라도 실패하면 전체가 롤백
	public void register(User user) {
		
			userRepository.save(user);
			
	}
	
	@Transactional(readOnly = true) //select 할 때 트랜잭션 시작, 서비스 종료시에 트랙잭션 종료 ( 정합성 유지 )
	public User login(User user) {
		return userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
	}
}
