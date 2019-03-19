package org.marceloleite.tutorials.spring.cloud.hystrix.rest.consumer;

import org.marceloleite.tutorials.spring.cloud.hystrix.rest.producer.GreetingController;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
		name = "rest-producer",
		url = "http://localhost:8081",
		fallback = GreetingClient.GreetingClientFallback.class)
public interface GreetingClient extends GreetingController {

	@Component
	public static class GreetingClientFallback implements GreetingClient {

		@Override
		public String greeting(@PathVariable("username") String username) {
			return "Hello User!";
		}
	}
}