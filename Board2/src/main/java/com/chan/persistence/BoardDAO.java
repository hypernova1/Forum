package com.chan.persistence;

import java.util.HashMap;
import java.util.List;

import com.chan.domain.BoardVO;
import com.chan.pagination.Criteria;

public interface BoardDAO {
	
	public void create(BoardVO vo);
	public void update(BoardVO vo);
	public HashMap<String, Object> read(Integer bno);
	public List<HashMap<String, Object>> readAll(Criteria cri);
	public int countAll();
	public void delete(Integer bno);
	public void viewUpdate(Integer bno);
	public void increasecom(Integer bno);
	public void decreasecom(Integer bno);
	public void increaserecom(Integer bno);
	public void decreaserecom(Integer bno);
}
