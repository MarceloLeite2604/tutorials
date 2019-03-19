package org.marceloleite.tutorials.spring.cloud.hystrix.rest.consumer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class GreetingService {

	private static final String DEFAULT_MESSAGE = "Hello User!";

	@HystrixCommand(fallbackMethod = "defaultGreeting")
	public String getGreeting(String username) {
		return new RestTemplate().getForObject("http://localhost:8081/greeting/{username}",
				String.class, username);
	}

	@SuppressWarnings("unused")
	private String defaultGreeting(String username) {
		return DEFAULT_MESSAGE;
	}
}