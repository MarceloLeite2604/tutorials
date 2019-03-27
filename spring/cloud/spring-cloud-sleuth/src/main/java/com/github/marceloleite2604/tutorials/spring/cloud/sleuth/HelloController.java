package com.github.marceloleite2604.tutorials.spring.cloud.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

	@GetMapping("/hello")
	public String getHello() throws Exception {
		LOGGER.info("Requested a hello message.");
		/*
		 * if (Math.random() > 0.5) { throw new Exception("Out of cheese."); }
		 */
		return "Hello, world!";
	}

}
