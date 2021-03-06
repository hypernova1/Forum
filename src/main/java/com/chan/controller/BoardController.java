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
	public String board(Model model, @ModelAttribute Criteria cri, HttpSession session) {

		Pagination pagination = new Pagination();
		List<HashMap<String, Object>> list = boardService.readAllPost(cri);
		Map<String, Object> popular = boardService.getPopularPost();
		pagination.setCri(cri);
		pagination.setTotalCount(boardService.totalCount(cri));

		model.addAttribute("list", list);
		model.addAttribute("page", pagination);
		model.addAttribute("popular", popular);

		return "board/list";
	}

	@GetMapping("write")
	public String writeGet(BoardVO board) {
		return "board/write";
	}

	@PostMapping("write")
	public String writePost(BoardVO board, HttpSession session) {
		board.setWriter((Integer) session.getAttribute("mno"));
		return "redirect:./post?bno=" + boardService.writePost(board);
	}

	@GetMapping("post")
	public String post(Model model, @ModelAttribute Criteria cri, Integer bno, HttpServletRequest req,
			HttpServletResponse res, HttpSession session) {

		// 조회수 구현
		Cookie cookies[] = req.getCookies();
		Map<String, Object> map = new HashMap<>();
		if (req.getCookies() != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie obj = cookies[i];
				map.put(obj.getName(), obj.getValue());
			}
		}

	    // 저장된 쿠키중에 read_count 만 불러오기
	    String readCount = (String) map.get("read_count");
	     // 저장될 새로운 쿠키값 생성
	    String newReadCount = "|" + bno;

	    if ( StringUtils.indexOfIgnoreCase(readCount, newReadCount) == -1 ) {
	          // 없을 경우 쿠키 생성
	          Cookie cookie = new Cookie("read_count", readCount + newReadCount);
	         
	          res.addCookie(cookie);
	          boardService.viewUpdate(bno, session); // 업데이트 실행
	    }

		model.addAttribute("board", boardService.readPost(bno));

		return "board/post";
	}

	@GetMapping("modify")
	public String modifyGet(Integer bno, Model model, @ModelAttribute Criteria cri) {
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
	public String recommend(Integer bno, Integer mno, Criteria cri, boolean recom, Integer type) {
		boardService.updateRecommend(bno, mno, recom, type);

		return "redirect:./post?bno=" + bno + "&page=" + cri.getPage();
	}
}
