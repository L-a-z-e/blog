package com.laze.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter -getter 메소드 자동 관리
//@Setter -setter 메소드 자동 관리
//@Data - getter + setter 메소드 자동 관리
//@AllArgsConstructor - 모든 Args 다 쓰는 생성자
//@RequiredArgsConstructor - final 로 변수 만들꺼면

@Data

@NoArgsConstructor
public class Member {
	
	private  int id;
	private  String userName;
	private  String password;
	private  String email;
	
	
	@Builder
	public Member(int id, String userName, String password, String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
	


}
