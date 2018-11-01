package com.chan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chan.domain.BoardVO;
import com.chan.persistence.BoardDAO;

import pagination.Criteria;

@Service
public class BoardService {

	@Autowired
	private BoardDAO dao;
	
	public void writePost(BoardVO vo) {
		dao.create(vo);
	}
	
	public BoardVO readPost(Integer bno) {
		return dao.read(bno);
	}
	
	public List<BoardVO> readAllPost(Criteria cri){
		return dao.readAll(cri);
	}
	
	public void updatePost(BoardVO vo) {
		dao.update(vo);
	}
	
	public void deletePost(Integer bno) {
		dao.delete(bno);
	}
}
