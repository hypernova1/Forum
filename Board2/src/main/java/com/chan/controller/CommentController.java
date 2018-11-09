package com.chan.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chan.domain.CommentVO;
import com.chan.service.CommentService;

@RestController
@RequestMapping("comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	//리스트, 갯수 리턴
	//TODO: 계속 insert null값 나옴
	@PostMapping("/write")
	public ResponseEntity<Map<String, Object>> writeComment(@RequestBody CommentVO vo) {
		commentService.writeComment(vo);
		
		return new ResponseEntity<>(commentService.readComment(vo.getBno(), vo.getType()), HttpStatus.OK);
	}
	
	@GetMapping("modify")
	public ResponseEntity<?> modifyGet(){
		
		return null;
	}
	
	@PostMapping("modify")
	public ResponseEntity<Map<String, Object>> modifyPost(@RequestBody CommentVO vo){
		commentService.updateComment(vo);
		
		return new ResponseEntity<>(commentService.readComment(vo.getBno(), vo.getType()), HttpStatus.OK);
	}
	
	@PostMapping("remove")
	public ResponseEntity<Map<String, Object>> removeComment(@RequestBody CommentVO vo){
		
		commentService.deleteComment(vo.getBno(), vo.getCno(), vo.getType());
		
		return new ResponseEntity<>(commentService.readComment(vo.getBno(), vo.getType()), HttpStatus.OK);
	}
	
	@GetMapping("read")
	public ResponseEntity<Map<String, Object>> commentlist(int bno, int type){
		
		return new ResponseEntity<>(commentService.readComment(bno, type), HttpStatus.OK);
	}
	
}
