package com.chan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chan.domain.BoardVO;
import com.chan.service.BoardService;

import pagination.Criteria;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("list")
	public String board(Model model, Criteria cri) {
		
		model.addAttribute(service.readAllPost(cri));
		return "board/list";
	}
	
	@GetMapping("write")
	public String writeGet(BoardVO board) {
		return "board/write";
	}
	@PostMapping("write")
	public String writePost(BoardVO board, String pw) {
		return "redirect:./list";
	}
	
	@GetMapping("post")
	public String post() {
		return "board/post"; 
	}
}
