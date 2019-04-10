package com.github.marceloleite2604.tutorials.spring.cloud.remote.properties.client;

import java.util.Optional;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	@Inject
	@Named("programProperties")
	private Properties programProperties;

	@RequestMapping("/message")
	public String getMessage() {
		return Optional.ofNullable(programProperties.get("message"))
				.map(Object::toString)
				.orElse("No message retrieved.");
	}
}