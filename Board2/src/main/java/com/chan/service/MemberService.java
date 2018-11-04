package com.chan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chan.domain.MemberVO;
import com.chan.persistence.MemberDAO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO dao;
	
	public int idcheck(String id) {
		return dao.idcheck(id);
	}
	
	public void join(MemberVO vo) {
		dao.create(vo);
	}
	
	public int login(MemberVO vo) {
		System.out.println(dao.getuser(vo.getId()));
		if(dao.getuser(vo.getId()) != null) {
			if(dao.getpw(vo.getPw()) == dao.getuser(vo.getId()).getPw()) {
				return 2; //비번 틀림
			} else {
				System.out.println("로그인");
				return 0; //로그인 완료
			}
		} 
		System.out.println("없는아이디");
		return 1; //없는 아이디
		
	}
}
