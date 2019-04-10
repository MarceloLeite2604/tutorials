package com.github.marceloleite2604.tutorials.spring.zuul.ui.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "index";
	}

}