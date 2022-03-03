package com.laze.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laze.blog.model.User;

//DAO
//자동으로 bean 등록이 된다 그래서 @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {
	
	
}

//JPA Naming 쿼리 전략 select *from user where userName = ? and password = ?
	//User findByUserNameAndPassword(String userName,String password);
	
	
	// 위에랑 똑같은 로직 - 네이티브 쿼리 사용
	//@Query(value = "SELECT * FROM user WHERE userName=?1 AND password=?2",nativeQuery = true)
	//User login(String userName, String password);
