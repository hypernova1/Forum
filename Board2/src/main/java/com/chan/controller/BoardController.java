package com.chan.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chan.domain.BoardVO;
import com.chan.pagination.Criteria;
import com.chan.pagination.Pagination;
import com.chan.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("list")
	public String board(Model model, Criteria cri) {
		Pagination pagination = new Pagination();
		List<HashMap<String, Object>> list = service.readAllPost(cri);

		pagination.setCri(cri);
		pagination.setTotalCount(service.totalCount());
		
		model.addAttribute("list", list);
		model.addAttribute("page", pagination);
		
		return "board/list";
	}
	
	@GetMapping("write")
	public String writeGet(BoardVO board) {
		return "board/write";
	}
	@PostMapping("write")
	public String writePost(BoardVO board) {
		board.setWriter(1);
		service.writePost(board);
		return "redirect:./list";
	}
	
	@GetMapping("post")
	public String post(Model model, Criteria cri, Integer bno) {
		model.addAttribute("board",service.readPost(bno));
		return "board/post"; 
	}
}
