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
		
				// 조회수 구현
				Cookie cookies[] = req.getCookies();
				Map<String, Object> map = new HashMap<>();
				if (req.getCookies() != null) {
					for (int i = 0; i < cookies.length; i++) {
						map.put(cookies[i].getName(), cookies[i].getValue());
					}
				}

			    // 저장된 쿠키중에 read_count 만 불러오기
			    String readCount = (String) map.get("read_count");
			     // 저장될 새로운 쿠키값 생성
			    String newReadCount = "|" + qno;

			    if (StringUtils.indexOfIgnoreCase(readCount, newReadCount) == -1) {
			          // 없을 경우 쿠키 생성
			          Cookie cookie = new Cookie("read_count", readCount + newReadCount);
			         
			          res.addCookie(cookie);
			          qnaService.viewUpdate(qno, session); // 업데이트 실행
			    }

				model.addAttribute("qna", qnaService.readPost(qno));

				return "/qna/post";
	}
	
	@GetMapping("modify")
	public String modifyGet(Integer qno, Model model, @ModelAttribute Criteria cri) {
		model.addAttribute("qna", qnaService.readPost(qno));
		return "/qna/modify";
	}
	
	@PostMapping("modify")
	public String modifyPost(Criteria cri, QnaVO vo) {
		
		qnaService.updatePost(vo);
		
		return "redirect:/qna/post?qno=" + vo.getQno() + "&page=" + cri.getPage();
	}
	
	@PostMapping("delete")
	public String delete(QnaVO vo, Criteria cri) {
		qnaService.deletePost(vo.getQno());
		
		return "redirect:/qna/list?page=" + cri.getPage();
	}
	
}
