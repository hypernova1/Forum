package com.chan.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chan.domain.CommentVO;
import com.chan.service.CommentService;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;

	//리스트, 갯수 리턴
	//TODO: 계속 insert null값 나옴
	@PostMapping("writecomment")
	public ResponseEntity<Map<String, Object>> writeComment(@RequestBody CommentVO vo) {
		System.out.println(vo.getMno());
		commentService.writeComment(vo);
		
		return new ResponseEntity<>(commentService.returnList(vo.getBno()), HttpStatus.OK);
	}
	
	@PostMapping("updatecomment")
	public ResponseEntity<Map<String, Object>> updateComment(@RequestBody CommentVO vo){
		
		commentService.updateComment(vo);
		
		return new ResponseEntity<>(commentService.returnList(vo.getBno()), HttpStatus.OK);
	}
	
	@GetMapping("readcomment")
	public ResponseEntity<Map<String, Object>> commentlist(@RequestParam int bno){
		
		System.out.println("왜 안돼");
		return new ResponseEntity<>(commentService.readComment(bno), HttpStatus.OK);
	}
	
	@DeleteMapping("deletecomment")
	public ResponseEntity<Map<String, Object>> deleteComment(Integer bno, Integer cno){
		
		commentService.deleteComment(bno, cno);
		
		return new ResponseEntity<>(commentService.returnList(bno), HttpStatus.OK);
	}
	
	
	
}
