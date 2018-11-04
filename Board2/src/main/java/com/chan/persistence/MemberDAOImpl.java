package com.chan.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chan.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession session;

	@Override
	public void create(MemberVO vo) {
		session.insert("member.create", vo);
	}

	@Override
	public void update(MemberVO vo) {
		session.update("member.update", vo);
	}

	@Override
	public int idcheck(String id) {
		return session.selectOne("member.idcheck", id);
	}

	@Override
	public String getpw(String pw) {
		return session.selectOne("member.getpw", pw);
	}

	@Override
	public MemberVO getuser(String id) {
		return session.selectOne("member.getuser", id);
	}

	@Override
	public int getuserid(String id) {
		return session.selectOne("member.getuserid", id);
	}
	
	

}
