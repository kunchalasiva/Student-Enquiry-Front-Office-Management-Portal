package com.nt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.binding.SignUpForm;
import com.nt.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService service;
	
	@GetMapping("/singup") 
	public String userSingup(Map<String,Object> map) {
	 map.put("singup", new SignUpForm());
		return "singup";
	}

	@PostMapping("/singup")
	public String userRegister(@ModelAttribute("singup") SignUpForm form,Map<String,Object> map) {
		// use the service
		boolean flag=service.registerUser(form);
		if(flag==true) {
			map.put("message", "Check your Email");
		}else {
			map.put("message", "email already taken try another");
		}
		return "singup";
		
	}
	
	@GetMapping("/login")
	public String userlogin() {
		return "login";
	}
	
	
	@GetMapping("/unlock")
	public String userUnlock(@RequestParam("email") String email,Model map) {
		map.addAttribute("email",email);
		return "unlock";
	}
	
	@GetMapping("/course")
	public String courseContent() {
		return "course";
	}
	
	@GetMapping("/forgotpwd")
	public String forgotpassword() {
		return "forgotpwd";
	}
}
