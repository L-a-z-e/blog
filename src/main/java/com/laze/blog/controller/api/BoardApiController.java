package com.laze.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laze.blog.config.auth.PrincipalDetail;
import com.laze.blog.dto.ResponseDto;
import com.laze.blog.model.Board;
import com.laze.blog.model.Reply;
import com.laze.blog.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	


	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.boardWrite(board,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); 
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.deleteBoard(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> updateForm(@PathVariable int id,@RequestBody Board board){
		
		System.out.println("BoardApiController : update : id :" + id);
		System.out.println("BoardApiController : update : board :" + board);
		
		System.out.println("BoardApiController : update : title :" + board.getTitle());
		System.out.println("BoardApiController : update : content :" + board.getContent());
		boardService.update(id,board);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
		
	}
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int boardId,@RequestBody Reply reply,@AuthenticationPrincipal PrincipalDetail principal){
		

		
		boardService.replySave(principal.getUser(), boardId, reply);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	
}
