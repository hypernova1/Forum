package com.chan.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chan.domain.CommentVO;
import com.chan.persistence.BoardDAO;
import com.chan.persistence.CommentDAO;

@Service
public class CommentService {

	@Autowired
	private CommentDAO dao;
	@Autowired
	private BoardDAO boardDao;

	public void writeComment(CommentVO vo) {
		boardDao.increasecom(vo.getBno());
		dao.create(vo);
	}

	public void updateComment(CommentVO vo) {
		dao.update(vo);
	}

	public Map<String, Object> readComment(Integer bno) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh.mm");
		Map<String, Object> comment = new HashMap<>();
		List<HashMap<String, Object>> list = new ArrayList<>();
		
		for(int i = 0; i < dao.readAll(bno).size(); i++) {
			HashMap<String, Object> obj = new HashMap<>();
			obj.put("bno", dao.readAll(bno).get(i).get("bno"));
			obj.put("cno", dao.readAll(bno).get(i).get("cno"));
			obj.put("regdate", sdf.format(dao.readAll(bno).get(i).get("regdate")));
			obj.put("name", dao.readAll(bno).get(i).get("name"));
			obj.put("content", dao.readAll(bno).get(i).get("content"));
			obj.put("mno", dao.readAll(bno).get(i).get("mno"));
			
			list.add(obj);
		}
		comment.put("count", dao.count(bno));
		comment.put("list", list);
		
		return comment;
	}

	public void deleteComment(Integer bno, Integer cno) {
		boardDao.decreasecom(bno);
		dao.delete(bno, cno);
	}

	public int countComment(Integer bno) {
		return dao.count(bno);
	}
}
