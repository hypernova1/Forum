package com.chan.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chan.domain.BoardVO;
import com.chan.pagination.Criteria;
import com.chan.persistence.BoardDAO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO dao;
	
	public void writePost(BoardVO vo) {
		vo.setOriginno(1);
		dao.create(vo);
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
	
	public int totalCount() {
		return dao.countAll();
	}
	
	public void viewUpdate(Integer bno) {
		dao.viewUpdate(bno);
	}
	
}
