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
import com.chan.persistence.QnaDAO;

@Service
public class CommentService {

	@Autowired
	private CommentDAO dao;
	@Autowired
	private BoardDAO boardDao;
	@Autowired
	private QnaDAO qnaDao;

	public void writeComment(CommentVO vo) {
		if(vo.getType() == 1) boardDao.increasecom(vo.getBno());
		else qnaDao.increasecom(vo.getBno());
		dao.create(vo);
	}

	public void updateComment(CommentVO vo) {
		dao.update(vo);
	}

	public Map<String, Object> readComment(Integer bno, Integer type) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh.mm");
		List<HashMap<String, Object>> oldList = dao.readAll(bno, type);
		List<HashMap<String, Object>> list = new ArrayList<>();
		Map<String, Object> comment = new HashMap<>();
		
		for(int i = 0; i < dao.readAll(bno, type).size(); i++) {
			HashMap<String, Object> obj = new HashMap<>();
			obj.put("bno", oldList.get(i).get("bno"));
			obj.put("cno", oldList.get(i).get("cno"));
			obj.put("regdate", sdf.format(oldList.get(i).get("regdate")));
			obj.put("name", oldList.get(i).get("name"));
			obj.put("content", oldList.get(i).get("content"));
			obj.put("mno", oldList.get(i).get("mno"));
			
			list.add(obj);
		}
		comment.put("count", dao.count(bno, type));
		comment.put("list", list);
		
		return comment;
	}

	public void deleteComment(Integer bno, Integer cno, Integer type) {
		if(type == 1) {
			boardDao.decreasecom(bno);
		} else {
			qnaDao.decreasecom(bno);
		}
		dao.delete(bno, cno);
	}

	public int countComment(Integer bno, Integer type) {
		return dao.count(bno, type);
	}
}
