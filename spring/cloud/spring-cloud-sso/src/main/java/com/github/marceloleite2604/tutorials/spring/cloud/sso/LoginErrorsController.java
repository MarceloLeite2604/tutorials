package com.github.marceloleite2604.tutorials.spring.cloud.sso;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginErrorsController {

	@GetMapping("/dashboard/login")
	public String dashboard() {
		return "redirect:/#/";
	}
}
