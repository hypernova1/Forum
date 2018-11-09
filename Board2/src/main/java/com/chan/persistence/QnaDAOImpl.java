package com.chan.persistence;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chan.domain.QnaVO;
import com.chan.pagination.Criteria;

@Repository
public class QnaDAOImpl implements QnaDAO {

	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(QnaVO vo) {
		session.insert("qna.insert", vo);
	}

	@Override
	public void updateGroup(Integer qno) {
		session.update("qna.groupUpdate", qno);
	}

	@Override
	public void update(QnaVO vo) {
		session.update("qna.update", vo);
	}

	@Override
	public HashMap<String, Object> read(Integer qno) {
		return session.selectOne("qna.read", qno);
	}

	@Override
	public List<HashMap<String, Object>> readAll(Criteria cri) {
		return session.selectList("qna.readAll", cri);
	}

	@Override
	public int countAll(Criteria cri) {
		return session.selectOne("qna.countAll", cri);
	}

	@Override
	public void delete(Integer qno) {
		session.delete("qna.delete", qno);
	}

	@Override
	public void viewUpdate(Integer qno) {
		session.update("qna.viewUpdate", qno);
	}

	@Override
	public void increasecom(Integer qno) {
		session.update("qna.increasecom", qno);
	}

	@Override
	public void decreasecom(Integer qno) {
		session.update("qna.decreasecom", qno);
	}

	@Override
	public void increaserecom(Integer qno) {
		session.update("qna.increaserecom", qno);
	}

	@Override
	public void decreaserecom(Integer qno) {
		session.update("qna.decreaserecom", qno);
	}

	@Override
	public List<QnaVO> popularPost() {
		return session.selectList("qna.popularPost");
	}

}
