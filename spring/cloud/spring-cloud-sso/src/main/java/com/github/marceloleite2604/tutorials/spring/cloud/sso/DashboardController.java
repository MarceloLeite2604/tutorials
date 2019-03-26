package com.github.marceloleite2604.tutorials.spring.cloud.sso;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@GetMapping("/message")
	public Map<String, Object> dashboard() {
		return Collections.<String, Object>singletonMap("message", "Yay!");
	}

	@GetMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

}
