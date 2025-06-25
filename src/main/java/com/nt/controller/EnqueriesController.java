package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
//@RequestMapping("/enquery")
public class EnqueriesController {

	@Autowired
	private HttpSession session;
	
	@GetMapping("dashboard")
	public String dashboard() {
		return "dashboard";
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
	 return "index";	
	}
	
	@GetMapping("/add")
	public String addEnquery() {
		return "addenquery";
	}
	
	@GetMapping("/view")
	public String view_Enqueries() {
		return "viewenqueries";
	}
	
}
