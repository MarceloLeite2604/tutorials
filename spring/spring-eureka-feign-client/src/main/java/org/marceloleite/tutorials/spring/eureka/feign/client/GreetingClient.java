package org.marceloleite.tutorials.spring.eureka.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("spring-cloud-eureka-client")
public interface GreetingClient {
	
	@RequestMapping("/greeting")
	String greeting();
}