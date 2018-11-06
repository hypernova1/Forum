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
	public void insert(Integer bno, Integer mno) {
		Map<String, Object> param = new HashMap<>();
		System.out.println(bno + " " + mno);
		param.put("bno", bno);
		param.put("mno", mno);
		session.insert("recommend.insert", param);
	}

	@Override
	public int select(Integer bno, Integer mno) {
		Map<String, Object> param = new HashMap<>();
		param.put("bno", bno);
		param.put("mno", mno);
		
		return session.selectOne("recommend.select", param);
	}

}
