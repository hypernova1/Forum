package com.chan.persistence;

import java.util.HashMap;
import java.util.List;

import com.chan.domain.CommentVO;

public interface CommentDAO {

	public void create(CommentVO vo);
	public void update(CommentVO vo);
	public List<HashMap<String, Object>> readAll(Integer bno);
	public void delete(Integer bno, Integer cno);
	public int count(Integer bno);
	public List<CommentVO> currentComment();
}
