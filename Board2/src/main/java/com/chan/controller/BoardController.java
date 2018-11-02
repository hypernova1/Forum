package com.chan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chan.domain.BoardVO;
import com.chan.pagination.Criteria;
import com.chan.pagination.Pagination;
import com.chan.service.BoardService;
import com.chan.service.CommentService;
import com.mysql.cj.util.StringUtils;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private CommentService commentService;
	
	@GetMapping("list")
	public String board(Model model, Criteria cri) {
		Pagination pagination = new Pagination();
		List<HashMap<String, Object>> list = boardService.readAllPost(cri);
		System.out.println("dasdsdadas");
		pagination.setCri(cri);
		pagination.setTotalCount(boardService.totalCount());
		
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
		System.out.println("포스트라이트");
		board.setWriter(1);
		boardService.writePost(board);
		return "redirect:./list";
	}
	
	@GetMapping("post/{bno}")
	public String post(Model model, Criteria cri, @PathVariable Integer bno, HttpServletRequest req, HttpServletResponse res) {
		//조회수 구현
		Cookie cookies[] = req.getCookies();
		Map<String, String> map = new HashMap<>();
		
		if(req.getCookies() != null) {
			for(int i = 0; i<cookies.length; i++) {
				Cookie obj = cookies[i];
				map.put(obj.getName(), obj.getValue());
			}
		}
		
		String readCount = map.get("read_Count");
		String newReadCount = "|" + bno;
		
		if(StringUtils.indexOfIgnoreCase(readCount, newReadCount) == -1) {
			Cookie cookie = new Cookie("read_Count", readCount + newReadCount);
			
			res.addCookie(cookie);
			boardService.viewUpdate(bno);
		}
		System.out.println(boardService.readPost(bno).get("mno"));
		model.addAttribute("board",boardService.readPost(bno));
		return "board/post"; 
	}
}
