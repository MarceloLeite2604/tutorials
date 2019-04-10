package com.github.marceloleite2604.tutorials.spring.cloud.hystrix.rest.producer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface GreetingController {
	
    @GetMapping("/greeting/{username}")
    public String greeting(@PathVariable("username") String username);
}