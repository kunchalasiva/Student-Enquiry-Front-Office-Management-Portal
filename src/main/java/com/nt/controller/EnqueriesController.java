package com.nt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/enquery")
public class EnqueriesController {

	@GetMapping("dashboard")
	public String dashboard() {
		return "dashboard";
	}
}
