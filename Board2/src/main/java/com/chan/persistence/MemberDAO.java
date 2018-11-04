package com.chan.persistence;

import com.chan.domain.MemberVO;

public interface MemberDAO {

	public void create(MemberVO vo);
	public void update(MemberVO vo);
	public int idcheck(String id);
	public String getpw(String pw);
	public MemberVO getuser(String id);
	public int getuserid(String id);
}
