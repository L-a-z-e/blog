package com.laze.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder 
// @DynamicInsert  // Null인 값을 insert때 빼주기때문에 role 에 null이 있으면 빼고 default 값 들어감
		//ORM -> Java Object ( 다른언어도 마찬가지 ) -> 테이블로 매핑해주는 기술
@Entity //User 클래스가 MariaDB에 테이블 생성
public class User {
	
	@Id //Primarykey
	@GeneratedValue(strategy=GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)
	private String userName; // 아이디
	
	@Column(nullable = false, length = 100) // 해쉬 ( 비밀번호 암호화 )
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	//@ColumnDefault("'user'") " + ' 하고 문자열 또는 " 만 하고 숫자
	@Enumerated(EnumType.STRING)
	private RoleType role; // 나중에는 Enum 쓰는게 좋다. // admin, user, manage
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;

}
