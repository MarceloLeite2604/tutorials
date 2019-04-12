package com.github.marceloleite2604.tutorials.spring.security.database;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {

	@GetMapping(path = "/hello")
	public String get() {
		return "Hello, world.";
	}
}
