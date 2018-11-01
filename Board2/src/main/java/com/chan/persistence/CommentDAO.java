package com.chan.persistence;

import java.util.List;

import com.chan.domain.CommentVO;

public interface CommentDAO {

	public void create(CommentVO vo);
	public void update(CommentVO vo);
	public CommentVO read(Integer bno);
	public List<CommentVO> readAll();
	public void delete(Integer bno);
}
