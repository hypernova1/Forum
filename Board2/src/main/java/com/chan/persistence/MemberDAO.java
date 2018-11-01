package com.chan.persistence;

import com.chan.domain.MemberVO;

public interface MemberDAO {

	public void create();
	public void update();
	public MemberVO read();
	public void delete();
	
}
