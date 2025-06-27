package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.binding.Dashboard;
import com.nt.binding.StudentForm;
import com.nt.entity.Course;
import com.nt.entity.StudentEntity;
import com.nt.service.IEnquiryService;

import jakarta.servlet.http.HttpSession;

@Controller
//@RequestMapping("/enquery")
public class EnqueriesController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private IEnquiryService service;
	
	@GetMapping("dashboard")
	public String dashboard(Model map) {
		
		  Integer userid = (Integer) session.getAttribute("UserId");
		  System.out.println("Session Id"+userid);
		// use the service
			Dashboard dashboard=service.getEnquires(userid);
			
			map.addAttribute("totalenq",dashboard.getTotalEnqCount());
			map.addAttribute("enrollenq",dashboard.getEnrolledEnqCount());
			map.addAttribute("lostenq",dashboard.getLostEnqCount());
			
		return "dashboard";
	}
	
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
	 return "index";	
	}
	
	@GetMapping("/add")
	public String addEnquery(Model map) {
		
		// get all the course
		List<Course> courseData	=service.getAllCourse();
		List<com.nt.entity.Status> statusData = service.getAllStatus();
		
		map.addAttribute("courseData", courseData);
		map.addAttribute("statusData", statusData);
		map.addAttribute("student", new StudentForm());
		
		return "addenquery";
	}
	
	
	@PostMapping("/add")
	public String addStudentEnquires(@ModelAttribute StudentForm entity,RedirectAttributes model) {
		// use the service
		String message=service.addEnquiries(entity);
		model.addFlashAttribute("student", new StudentForm());
		model.addFlashAttribute("enqsave",message);
		System.out.println(entity);
		return "redirect:/add";
	}
	
	@GetMapping("/view")
	public String view_Enqueries() {
		return "viewenqueries";
	}
	
}
