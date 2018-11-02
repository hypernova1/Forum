package com.chan.persistence;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chan.domain.BoardVO;
import com.chan.pagination.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession session;

	@Override
	public void create(BoardVO vo) {
		session.insert("board.create", vo);
	}

	@Override
	public void update(BoardVO vo) {
		session.update("board.update", vo);
	}

	@Override
	public HashMap<String, Object> read(Integer bno) {
		return session.selectOne("board.read", bno);
	}

	@Override
	public List<HashMap<String, Object>> readAll(Criteria cri) {
		return session.selectList("board.readAll", cri);
	}

	@Override
	public void delete(Integer bno) {
		session.delete("board.delete", bno);
	}

	@Override
	public int countAll() {
		return session.selectOne("board.countAll");
	}

	@Override
	public void viewUpdate(Integer bno) {
		session.update("board.viewUpdate", bno);
	}

}
