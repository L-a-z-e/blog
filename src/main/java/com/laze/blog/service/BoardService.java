package com.laze.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laze.blog.model.Board;
import com.laze.blog.model.RoleType;
import com.laze.blog.model.User;
import com.laze.blog.repository.BoardRepository;
import com.laze.blog.repository.UserRepository;
// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌. IoC
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	
	
	@Transactional// 전체가 성공하면 성공, 하나라도 실패하면 전체가 롤백
	public void boardWrite(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
			
	}
	

}
