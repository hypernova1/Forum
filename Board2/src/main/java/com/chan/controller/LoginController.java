package com.chan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@GetMapping("/")
	public String home(){
		return "home";
	}
	
	
	@PostMapping("login")
	public @ResponseBody ResponseEntity<?> login(@RequestBody String json){
		System.out.println(json);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
}
