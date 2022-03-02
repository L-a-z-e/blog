package com.laze.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laze.blog.model.RoleType;
import com.laze.blog.model.User;
import com.laze.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당id는 존재하지 않습니다.";
		}
		
		
		return id+"삭제되었습니다.";
	}
	
	
	
	
	
	//save 함수는 id를 전달하지 않으면 insert
	// id를 전달하면 해당 id에 대한 데이터가 있으면 update, 없으면 insert
	
	 //함수 종료시에 자동 commit이 됨, 더티 체킹 예를들어 id = 2 인 User 객체 정보가 영속성 컨텐스트에 없으면 DB에서 영속성 컨텍스트 1차 캐시에 가져오고, 그걸 받아오는데 변경이 일어나면 
	@Transactional //@Transaction -> 함수 호출 종료시 커밋, 그래서 영속성 컨텍스트와 다르면 그부분 업데이트
	@PutMapping("/dummy/user/{id}") // @RequestBody -> json 데이터 요청 -> Java Object(메세지 컨버터 Jackson 라이브러리가 변환해서 받아줌 )
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		
		
		System.out.println("id :" + id);
		System.out.println("password :" + requestUser.getPassword());
		System.out.println("email :" + requestUser.getEmail());
	
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다");
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		//userRepository.save(user);
		// userRepository.save(requestUser); 이렇게 하면 requestUser에 비어있는 userName 이나 role 등은 null 로 들어감, 이유는 update가 아니라 insert가 수행되어서
		return user;
		
		
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		
		return userRepository.findAll();
		
	}
	
	//한페이지당 1건에 데이터를 리턴
	@GetMapping("/dummy/user")
	public List<User> pagelist(@PageableDefault(size = 1,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
		
		Page<User> pagingUsers = userRepository.findAll(pageable);
		
		
		List<User> users = pagingUsers.getContent();
		
		return users;
	}

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
