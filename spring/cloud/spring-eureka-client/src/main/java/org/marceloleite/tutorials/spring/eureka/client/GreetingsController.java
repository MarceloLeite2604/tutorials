package org.marceloleite.tutorials.spring.eureka.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("eureka-client")
public class GreetingsController {

	private static final String MENSAGEM = "Hello from Eureka client.";

	
	@RequestMapping("/greeting")
	public String greeting() {
		return MENSAGEM;
	}
}
