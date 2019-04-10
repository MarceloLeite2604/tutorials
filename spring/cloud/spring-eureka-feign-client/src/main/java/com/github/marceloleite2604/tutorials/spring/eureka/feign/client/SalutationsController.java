package com.github.marceloleite2604.tutorials.spring.eureka.feign.client;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("eureka-feign-client")
public class SalutationsController {

	@Inject
	private GreetingFeignClient greetingFeignClient;

	@RequestMapping("/salutation")
	public String greeting(Model model) {
		return greetingFeignClient.greeting();
	}
}
