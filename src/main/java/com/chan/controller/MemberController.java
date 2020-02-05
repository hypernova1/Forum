package com.chan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chan.domain.MemberVO;
import com.chan.service.MemberService;

@RestController
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/profile")
	public ResponseEntity<?> profileGet(Integer mno) {
		return new ResponseEntity<>(memberService.getProfile(mno), HttpStatus.OK);
	}
	
	@PostMapping("/profile")
	public ResponseEntity<?> profilePost(@RequestBody MemberVO vo){
		
		memberService.updateProfile(vo);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
}
