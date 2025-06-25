package com.nt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.binding.LogInForm;
import com.nt.binding.SignUpForm;
import com.nt.binding.UnlockForm;
import com.nt.entity.UserEntity;
import com.nt.service.IUserService;
import com.nt.utils.EmailUtil;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private IUserService service;
	
	@Autowired
	private EmailUtil utilsEmail;
	
	@GetMapping("/singup") 
	public String userSingup(Map<String,Object> map) {
	 map.put("singup", new SignUpForm());
		return "singup";
	}

	@PostMapping("/singup")
	public String userRegister(@ModelAttribute("singup") SignUpForm form,Map<String,Object> map) {
		UserEntity entity=service.getByUserEmail(form.getUserEmail());
		
		if(entity == null) {
		 // use service
			boolean flag=service.registerUser(form);
			  if(flag==true) {
				  map.put("message", "Account created!.. Check your email");
			  }else {
				  map.put("error", "something went wrong plese try again");
			  }
		}else {
		 map.put("emailerror", "email already exist try another email id");	
		}
		
		return "singup";
	}
	
	
	@GetMapping("/unlock")
	public String userUnlock(@RequestParam("email") String email, Model map) {
		// use the service
		UserEntity entity=service.getByUserEmail(email);
	    UnlockForm form = new UnlockForm();
	    System.out.println(entity.getUserEmail());
	    form.setMail(entity.getUserEmail());
	    map.addAttribute("email", form.getMail());
	    map.addAttribute("unlock", form);
	    return "unlock";
	}
	
	@PostMapping("/unlock")
	public String userUnlockAccount(@ModelAttribute("unlock") UnlockForm unlock,RedirectAttributes att) {
		UserEntity entity=service.getByUserEmail(unlock.getMail());
		System.out.println(entity);
	    
		 if(!unlock.getTempwd().equals(entity.getUserPwd())) {
			att.addFlashAttribute("tempwderror", "Invalid Tempory password"); 
		 }else if(!unlock.getNewpwd().equals(unlock.getConpwd())) {
			 att.addFlashAttribute("pwderror", "confirm password incorrect"); 
		 }else {
			 // use the service
			 boolean flag=service.unlockUserAccount(unlock);
			 att.addFlashAttribute("sucmsg", "Account unlocked you can login");
		 }
		return "redirect:/unlock?email="+entity.getUserEmail();

	}

	@GetMapping("/login")
	public String userlogin(Model map) {
		map.addAttribute("login",new LogInForm());
		return "login";
	}
	
	@PostMapping("/login")
	public String userloginCredentials(@ModelAttribute("login") LogInForm form,Model map) {
		//use the service
		String message=service.loginUser(form);
		
		
		if(message.contains("success")) {
			return "dashboard";
		}else {
			map.addAttribute("errorlogin", message);
		}
		return "login";
	}
	
	
	
	@GetMapping("/course")
	public String courseContent() {
		return "course";
	}
	
	@GetMapping("/forgotpwd")
	public String forgotpassword(Model map) {
		return "forgotpwd";
	}
	
	@PostMapping("/forgotpwd")
	public String recoverypassword(@RequestParam("email") String email,Model map) {
		// use the service
		 UserEntity entity =service.getByUserEmail(email);
		 
		 if(entity==null) {
		   map.addAttribute("emailerror","Email not exist");
		 }else {
			// passing the password to the email
			 String to = entity.getUserEmail();
			 String subject ="Recovery Password";
			 String body = "Your Account password : "+  entity.getUserPwd();
			 
			 utilsEmail.SendEmail(to, subject, body);
				 map.addAttribute("emailsucc","Check your email & login");
		 }
		 return "forgotpwd";
	}
	
}
