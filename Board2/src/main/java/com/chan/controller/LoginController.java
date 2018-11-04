package com.chan.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chan.domain.MemberVO;
import com.chan.service.MemberService;
import com.mysql.cj.Session;

@Controller
public class LoginController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/")
	public String home(){
		return "home";
	}
	
	@PostMapping("login")
	public @ResponseBody ResponseEntity<?> login(@RequestBody MemberVO vo, HttpSession session){

		if(memberService.login(vo) == 1 || memberService.login(vo) == 2) {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
		session.setAttribute("mno", memberService.getuserid(vo.getId()));
		
		return new ResponseEntity<>(memberService.getuserid(vo.getId()), HttpStatus.OK);
	}
	
	@PostMapping("login/idcheck")
	public @ResponseBody ResponseEntity<?> idcheck(@RequestBody MemberVO vo){
		if(memberService.idcheck(vo.getId()) == 1) {
			return new ResponseEntity<String>("fail", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
	}
	
	@PostMapping("login/join")
	public @ResponseBody ResponseEntity<?> join(@RequestBody MemberVO vo){
		 
		memberService.join(vo);
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@PostMapping("/login/logout")
	public @ResponseBody ResponseEntity<?> logout(HttpSession session){
		
		session.removeAttribute("mno");
		
		return null;
	}
}
