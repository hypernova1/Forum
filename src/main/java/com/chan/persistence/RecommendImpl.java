package com.chan.persistence;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecommendImpl implements RecommendDAO {

	@Autowired
	private SqlSession session;
	@Override
	public void insert(Integer bno, Integer mno, Integer type) {
		Map<String, Object> param = new HashMap<>();
		param.put("bno", bno);
		param.put("mno", mno);
		param.put("type", type);
		session.insert("recommend.insert", param);
	}

	@Override
	public int select(Integer bno, Integer mno, Integer type) {
		Map<String, Object> param = new HashMap<>();
		param.put("bno", bno);
		param.put("mno", mno);
		param.put("type", type);
		
		return session.selectOne("recommend.select", param);
	}

}
