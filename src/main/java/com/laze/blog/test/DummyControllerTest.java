package com.laze.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laze.blog.model.RoleType;
import com.laze.blog.model.User;
import com.laze.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;

	@PostMapping("/dummy/join")
	public String join(User user) {
		
		
		
		
		System.out.println("id: " + user.getId());
		System.out.println("username : " + user.getUserName());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate :" + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
		
	}
	//{id} 주소로 파라미터를 전달받을 수 있음
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		// user/2 가 끝인데 user/3을 DB에서 찾으면 못찾으니 user가 null이 된다.
		// return null, null 이 리턴돼서 프로그램에 문제가있음
		// Optional로 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return
		// 람다식 사용하면 new Supplier<IllegalArgumentException<> 이런거 안쓰고
		// findById(id).orElseThrow(()->{ return new IllegalArgumentException("해당사용자가 X");});로 처리가능
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
		@Override
		public IllegalArgumentException get() {
			// TODO Auto-generated method stub
			return new IllegalArgumentException("해당 유저는 없습니다. id :" + id);
		}
		}); // get() 확실히 있다면, orElseGet() -> null이면 빈객체 생성
		return user; // 유저객체는 java object 인데 return해주는게 html이 아니라 데이터
		// Data를 리턴해주는 Controller가 -> json으로 변환해서 던져줌 MessageConverter가 응답시에 자동 작동 (Jackson 라이브러리 호출해서 )
	}
}
