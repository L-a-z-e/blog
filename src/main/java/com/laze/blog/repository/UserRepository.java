package com.laze.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laze.blog.model.User;

//DAO
//자동으로 bean 등록이 된다 그래서 @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {
	

}
