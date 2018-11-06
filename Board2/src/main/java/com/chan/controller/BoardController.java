package com.chan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chan.domain.BoardVO;
import com.chan.pagination.Criteria;
import com.chan.pagination.Pagination;
import com.chan.service.BoardService;
import com.mysql.cj.util.StringUtils;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("list")
	public String board(Model model, Criteria cri, HttpSession session) {
		
		Pagination pagination = new Pagination();
		List<HashMap<String, Object>> list = boardService.readAllPost(cri);
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
	public String writePost(BoardVO board, HttpSession session) {
		System.out.println(session.getAttribute("mno"));
		board.setWriter((Integer) session.getAttribute("mno"));
		boardService.writePost(board);
		return "redirect:./list";
	}
	
	@GetMapping("post")
	public String post(Model model, @ModelAttribute Criteria cri, Integer bno
			, HttpServletRequest req, HttpServletResponse res, HttpSession session) {
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
		
		model.addAttribute("board",boardService.readPost(bno));
		return "board/post"; 
	}
	
	@GetMapping("modify")
	public String modifyGet(Integer bno, Model model, @ModelAttribute Criteria cri) {
		System.out.println(bno);
		model.addAttribute("board", boardService.readPost(bno));
		return "board/modify";
	}
	
	@PostMapping("modify")
	public String modifyPost(Criteria cri, BoardVO vo) {
		
		boardService.updatePost(vo);
		
		return "redirect:/board/post?bno=" + vo.getBno() + "&page=" + cri.getPage();
	}
	
	@PostMapping("delete")
	public String delete(BoardVO vo, Criteria cri) {
		boardService.deletePost(vo.getBno());
		
		return "redirect:./list?page=" + cri.getPage();
	}
	
	@GetMapping("recommend")
	public String recommend(Integer bno, Integer mno, Criteria cri, boolean recom) {
		System.out.println(bno + " " + mno + " " + cri.getPage());
		boardService.updateRecommend(bno, mno, recom);
		
		return "redirect:./post?bno=" + bno + "&page=" + cri.getPage();
	}
}
