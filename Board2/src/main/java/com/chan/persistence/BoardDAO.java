package com.chan.persistence;

import java.util.List;

import com.chan.domain.BoardVO;

import pagination.Criteria;

public interface BoardDAO {
	
	public void create(BoardVO vo);
	public void update(BoardVO vo);
	public BoardVO read(Integer bno);
	public List<BoardVO> readAll(Criteria cri);
	public void delete(Integer bno);

}
