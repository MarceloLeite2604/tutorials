package com.github.marceloleite2604.tutorials.spring.eureka.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("spring-cloud-eureka-client")
public interface GreetingFeignClient {
	
	@RequestMapping("eureka-client/greeting")
	String greeting();
}