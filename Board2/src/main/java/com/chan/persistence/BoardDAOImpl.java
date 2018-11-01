package com.chan.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.chan.domain.BoardVO;

import pagination.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
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
	public BoardVO read(Integer bno) {
		return session.selectOne("board.read", bno);
	}

	@Override
	public List<BoardVO> readAll(Criteria cri) {
		return session.selectList("board.readAll", cri);
	}

	@Override
	public void delete(Integer bno) {
		session.delete("board.delete", bno);
	}

}
