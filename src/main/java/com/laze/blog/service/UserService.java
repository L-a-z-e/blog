package com.laze.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laze.blog.model.RoleType;
import com.laze.blog.model.User;
import com.laze.blog.repository.UserRepository;
// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌. IoC
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Transactional// 전체가 성공하면 성공, 하나라도 실패하면 전체가 롤백
	public void register(User user) {
			
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			user.setPassword(encPassword);
			user.setRole(RoleType.USER);
			userRepository.save(user);
			
	}
	
	@Transactional
	public void update(User user) {
		//수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
		//select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날린다.
		User updatedUser = userRepository.findById(user.getId())
				.orElseThrow(()->{
					return new IllegalArgumentException("회원찾기 실패");
				});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		updatedUser.setPassword(encPassword);
		updatedUser.setEmail(user.getEmail());
		

		 
	}
	

}
