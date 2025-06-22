package com.nt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/login")
	public String userlogin() {
		return "login";
	}
	
	@GetMapping("/singup") 
	public String userSingup() {
		return "singup";
	}
	
	@GetMapping("/unlock")
	public String userUnlock() {
		return "unlock";
	}
	
	@GetMapping("/course")
	public String courseContent() {
		return "course";
	}
}
