package org.marceloleite.webvalidations.controller;

import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

import org.marceloleite.webvalidations.bo.GreetingBO;
import org.marceloleite.webvalidations.model.Greeting;
import org.marceloleite.webvalidations.model.validation.rest.GreetingRestValidation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	private static final String TEMPLATE = "Hello, %s!";

	private final AtomicLong counter = new AtomicLong();
	
	@Inject
	private GreetingBO greetingBO;

	@RequestMapping(method = RequestMethod.GET, path = "/greeting")
	public Greeting get(@RequestParam(value = "name", defaultValue = "World") String name) {
		return Greeting.builder()
				.id(counter.incrementAndGet())
				.content(String.format(TEMPLATE, name))
				.build();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/greeting")
	public Greeting post(@Validated(GreetingRestValidation.class) @RequestBody Greeting greeting) {
		return greetingBO.save(greeting);
	}
}
