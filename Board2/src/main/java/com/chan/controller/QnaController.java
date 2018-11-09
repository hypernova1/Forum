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

import com.chan.domain.QnaVO;
import com.chan.pagination.Criteria;
import com.chan.pagination.Pagination;
import com.chan.service.QnaService;
import com.mysql.cj.util.StringUtils;

@Controller
@RequestMapping("qna")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@GetMapping("list")
	public String board(Model model, @ModelAttribute Criteria cri, HttpSession session) {
		
		Pagination pagination = new Pagination();
		List<HashMap<String, Object>> list = qnaService.readAllQna(cri);
		pagination.setCri(cri);
		pagination.setTotalCount(qnaService.totalCount(cri));
		
		model.addAttribute("list", list);
		model.addAttribute("page", pagination);
		
		return "qna/list";
	}
	
	@GetMapping("write")
	public String writerGet(QnaVO vo, Model model) {
		model.addAttribute("qna", vo);
		return "/qna/write";
	}
	
	@PostMapping("write")
	public String writerPost(QnaVO vo, Criteria cri, HttpSession session) {
		vo.setMno((Integer) session.getAttribute("mno"));
		qnaService.writeQna(vo);
		return "redirect:./post?qno=" + vo.getQno();
	}
	
	@GetMapping("post")
	public String post(Model model, @ModelAttribute Criteria cri, Integer qno
			, HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		System.out.println(qnaService.readPost(qno).toString());
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
		String newReadCount = "|" + qno;
		
		if(StringUtils.indexOfIgnoreCase(readCount, newReadCount) == -1) {
			Cookie cookie = new Cookie("read_Count", readCount + newReadCount);
			
			res.addCookie(cookie);
			qnaService.viewUpdate(qno);
		}
		model.addAttribute("qna",qnaService.readPost(qno));
		return "qna/post"; 
	}
	
}
