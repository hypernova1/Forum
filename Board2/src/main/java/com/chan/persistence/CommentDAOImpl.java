package com.chan.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chan.domain.CommentVO;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SqlSession session;
	
	@Override
	public void create(CommentVO vo) {
		session.insert("comment.create", vo);
	}

	@Override
	public void update(CommentVO vo) {
		System.out.println(vo.getCno());
		session.update("comment.update", vo);
	}

	@Override
	public List<HashMap<String, Object>> readAll(Integer bno, Integer type) {
		Map<String, Object> param = new HashMap<>();
		param.put("bno", bno);
		param.put("type", type);
		return session.selectList("comment.readAll", param);
	}


	@Override
	public void delete(Integer bno, Integer cno) {
		Map<String, Object> param = new HashMap<>();
		param.put("bno", bno);
		param.put("cno", cno);
		session.delete("comment.delete", param);
	}

	@Override
	public int count(Integer bno, Integer type) {
		Map<String, Object> param = new HashMap<>();
		param.put("bno", bno);
		param.put("type", type);
		return session.selectOne("comment.count", param);
	}

	@Override
	public List<CommentVO> currentComment() {
		return session.selectList("comment.currentComment");
	}

}
