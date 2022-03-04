package com.laze.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laze.blog.model.Board;
import com.laze.blog.model.User;
import com.laze.blog.repository.BoardRepository;
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
	
	@Transactional(readOnly = true)
	public Page<Board> boardlist(Pageable pageable){
		
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board readBoard(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
				});
	}
	
	@Transactional
	public void deleteBoard(int id) {
		 boardRepository.deleteById(id);
			
	}
	
	@Transactional
	public void update(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
		.orElseThrow(()->{
			return new IllegalArgumentException("글 수정하기 실패 : 아이디를 찾을 수 없습니다.");
		});
		
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		
		
	}
	
}
