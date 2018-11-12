package com.chan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EtcComtroller {

	
	@GetMapping("/")
	public String home(){
		return "home";
	}
	
	@GetMapping("/contact")
	public String notice() {
		return "contact";
	}
}
