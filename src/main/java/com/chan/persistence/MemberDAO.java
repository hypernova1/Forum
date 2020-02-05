package com.chan.persistence;

import com.chan.domain.MemberVO;

public interface MemberDAO {

	public void create(MemberVO vo);
	public void update(MemberVO vo);
	public int idcheck(String id);
	public String getpw(String pw);
	public MemberVO getuserById(String id);
	public MemberVO getuserByMno(Integer Mno);
	public int getuserid(String id);
	public void tempPw(MemberVO vo);
}
