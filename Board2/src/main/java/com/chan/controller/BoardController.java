package com.chan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("board")
	public String board() {
		return "board";
	}
}
