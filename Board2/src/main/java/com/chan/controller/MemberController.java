package com.chan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chan.service.MemberService;

@RestController
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("profile")
	public ResponseEntity<?> profile(String id) {
		
		return new ResponseEntity<>(memberService.getProfile(id), HttpStatus.OK);
	}
}
