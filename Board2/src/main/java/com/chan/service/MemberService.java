package com.chan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.chan.domain.MemberVO;
import com.chan.persistence.MemberDAO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO dao;
	@Autowired
	private JavaMailSender jms;
	
	public int idcheck(String id) {
		return dao.idcheck(id);
	}
	
	public void join(MemberVO vo) {
		dao.create(vo);
	}
	
	public int login(MemberVO vo) {
		
		if(dao.getuserById(vo.getId()) != null) {
			if(dao.getpw(vo.getPw()).equals(dao.getuserById(vo.getId()).getPw())) {
				System.out.println("로그인");
				return 0; //로그인 완료
			} else {
				System.out.println("비번틀림");
				return 2; //비번 틀림
			}
		} 
		System.out.println("없는아이디");
		return 1; //없는 아이디
		
	}
	
	public int getuserid(String id) {
		return dao.getuserid(id);
	}
	
	public MemberVO getProfile(Integer mno) {
		return dao.getuserByMno(mno);
	}
	
	public void updateProfile(MemberVO vo) {
		
		dao.update(vo);
	}

	public String temporaryPassword(String id) {
		
		MailHandler mh = new MailHandler(jms);
		String key = new TempKey().getKey(8, true);
		MemberVO vo = dao.getuserById(id);
		
		vo.setPw(key);
		
		dao.tempPw(vo);
		
		
		mh.setSubject("[Developers] 임시 비밀번호입니다.");
		mh.setText(key);
		mh.setFrom("chtlstjd01@gmail.com", "운영자");
		mh.setTo(vo.getEmail());
		mh.send();
		
		return vo.getEmail();
	}
	
}
