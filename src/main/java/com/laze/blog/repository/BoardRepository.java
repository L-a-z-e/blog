package com.laze.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laze.blog.model.Board;


public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	
}


