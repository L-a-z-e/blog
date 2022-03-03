package com.laze.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.laze.blog.model.User;
import com.laze.blog.repository.UserRepository;

@Service //Bean 등록
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	// 스프링이 로그인 요청을 가로챌때 username,password 변수 2개를 가로채는데
	// password 부분 처리는 알아서 함
	//username이 DB에 있는지만 확인
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User principal = userRepository.findByUserName(userName).orElseThrow(()->{
			return new UsernameNotFoundException("해당사용자를 찾을 수 없습니다."+userName);
		});
		return new PrincipalDetail(principal); //시큐리티 세션에 유저정보가 저장
	}

	
}
