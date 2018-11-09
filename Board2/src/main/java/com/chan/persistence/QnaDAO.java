package com.chan.persistence;

import java.util.HashMap;
import java.util.List;

import com.chan.domain.QnaVO;
import com.chan.pagination.Criteria;

public interface QnaDAO {

	public void insert(QnaVO vo);
	public void updateGroup(Integer qno);
	public void update(QnaVO vo);
	public HashMap<String, Object> read(Integer qno);
	public List<HashMap<String, Object>> readAll(Criteria cri);
	public int countAll(Criteria cri);
	public void delete(Integer qno);
	public void viewUpdate(Integer qno);
	public void increasecom(Integer qno);
	public void decreasecom(Integer qno);
	public void increaserecom(Integer qno);
	public void decreaserecom(Integer qno);
	public List<QnaVO> popularPost();
}
