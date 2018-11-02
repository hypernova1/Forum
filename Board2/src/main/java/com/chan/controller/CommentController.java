package com.chan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		System.out.println(vo.getBno());
		commentService.writeComment(vo);
		
		return new ResponseEntity<>(commentService.returnList(vo.getBno(), vo.getCno()), HttpStatus.OK);
	}
	
	@PostMapping("updatecomment")
	public ResponseEntity<Map<String, Object>> updateComment(@RequestBody CommentVO vo){
		
		commentService.updateComment(vo);
		
		return new ResponseEntity<>(commentService.returnList(vo.getBno(), vo.getCno()), HttpStatus.OK);
	}
	
	@GetMapping("readcomment")
	public ResponseEntity<List<HashMap<String, Object>>> commentlist(Integer bno, Integer cno){
		
		return new ResponseEntity<List<HashMap<String, Object>>>(commentService.readComment(bno, cno), HttpStatus.OK);
	}
	
	@DeleteMapping("deletecomment")
	public ResponseEntity<Map<String, Object>> deleteComment(Integer bno, Integer cno){
		
		commentService.deleteComment(bno, cno);
		
		return new ResponseEntity<>(commentService.returnList(bno, cno), HttpStatus.OK);
	}
	
	
	
}
