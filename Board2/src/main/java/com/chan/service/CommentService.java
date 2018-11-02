package com.chan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chan.domain.CommentVO;
import com.chan.persistence.CommentDAO;

@Service
public class CommentService {

	@Autowired
	private CommentDAO dao;

	public void writeComment(CommentVO vo) {
		dao.create(vo);
	}

	public void updateComment(CommentVO vo) {
		dao.update(vo);
	}

	public List<HashMap<String, Object>> readComment(Integer bno, Integer cno) {
		return dao.readAll(bno, cno);
	}

	public void deleteComment(Integer bno, Integer cno) {
		dao.delete(bno, cno);
	}

	public int countComment() {
		return dao.count();
	}

	public Map<String, Object> returnList(Integer bno, Integer cno) {

		Map<String, Object> obj = new HashMap<>();
		obj.put("list", readComment(bno, cno));
		obj.put("count", countComment());

		return obj;
	}
}
