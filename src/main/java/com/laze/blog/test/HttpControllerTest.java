package com.laze.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String tag = "HttpController Test: ";
	
	
	@GetMapping("/http/lomboktest")
	public String lombokTest() {
		
		Member m1 = Member.builder().userName("ssar").password("1234").email("email").build();
		System.out.println(tag + "getter : " + m1.getUserName());
		m1.setUserName("1000");
		System.out.println(tag + "setter : " + m1.getUserName());
		System.out.println("userID :" + m1.getId());
		Member m2 = Member.builder().build();
		System.out.println(m2.getId()+m2.getPassword()+m2.getEmail()+m2.getUserName());
		return "lombok test 완료";
		
		
	}
	
	
	// 인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다.
	@GetMapping("/http/get")
	public String getTest(Member m) { //?id=1&userName=ssar&password=1234&email=ssar@naver.com 멤버 오브젝트에 넣어주는 역할을 Spring이 해줌
		
		return tag + "get 요청 : "+ m.getId() + ", " + m.getUserName() +"," + m.getPassword() +", "+m.getEmail();
	}
							
	
								// MessageConverter가 Json을 자동으로 파싱해서 Member에 넣어줌
	@PostMapping("/http/post") // text/plain , application/json
	public String postTest(@RequestBody Member m) {
		
		return tag + "post 요청 : "+ m.getId() + ", " + m.getUserName() +"," + m.getPassword() +", "+m.getEmail();
	}
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		
		return tag + "put 요청" + m.getId() + ", " + m.getUserName() +"," + m.getPassword() +", "+m.getEmail();
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		
		return tag + "delete 요청";
	}
	

}
