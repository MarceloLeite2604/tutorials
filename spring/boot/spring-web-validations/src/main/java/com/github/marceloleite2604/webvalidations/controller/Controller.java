package com.github.marceloleite2604.webvalidations.controller;

import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

import com.github.marceloleite2604.webvalidations.bo.GreetingBO;
import com.github.marceloleite2604.webvalidations.model.Greeting;
import com.github.marceloleite2604.webvalidations.model.validation.rest.GreetingRestValidation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	private static final String TEMPLATE = "Hello, %s!";

	private final AtomicLong counter = new AtomicLong();
	
	@Inject
	private GreetingBO greetingBO;

	@GetMapping("/greeting")
	public Greeting get(@RequestParam(value = "name", defaultValue = "World") String name) {
		return Greeting.builder()
				.id(counter.incrementAndGet())
				.content(String.format(TEMPLATE, name))
				.build();
	}

	@PostMapping("/greeting")
	public Greeting post(@Validated(GreetingRestValidation.class) @RequestBody Greeting greeting) {
		return greetingBO.save(greeting);
	}
}
