package com.chan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chan.domain.BoardVO;
import com.chan.pagination.Criteria;
import com.chan.persistence.BoardDAO;
import com.chan.persistence.CommentDAO;
import com.chan.persistence.RecommendDAO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO dao;
	@Autowired
	private RecommendDAO recommendDao;
	@Autowired
	private CommentDAO commentDao;
	
	public int writePost(BoardVO vo) {
		dao.create(vo);
		return vo.getBno();
	}
	
	public HashMap<String, Object> readPost(Integer bno) {
		return dao.read(bno);
	}
	
	public List<HashMap<String, Object>> readAllPost(Criteria cri){
		return dao.readAll(cri);
	}
	
	public void updatePost(BoardVO vo) {
		dao.update(vo);
	}
	
	public void deletePost(Integer bno) {
		dao.delete(bno);
	}
	
	public int totalCount(Criteria cri) {
		return dao.countAll(cri);
	}
	
	public void viewUpdate(Integer bno, HttpSession session) {
		if(!session.getAttribute("mno").equals(dao.read(bno).get("mno"))) {
			dao.viewUpdate(bno);
		}
	}
	
	public void updateRecommend(Integer bno, Integer mno, boolean recom, Integer type) {
		if(recommendDao.select(bno, mno, type) != 0) {
			return;
		}
		
		if (recom) {
			dao.increaserecom(bno);
		} else {
			dao.decreaserecom(bno);
		}
		recommendDao.insert(bno, mno, type);

	}
	
	public Map<String, Object> getPopularPost(){
		
		Map<String, Object> popular = new HashMap<>();
		popular.put("popularPost", dao.popularPost());
		popular.put("currentComment", commentDao.currentComment());
		
		return popular;
	}
	
}
