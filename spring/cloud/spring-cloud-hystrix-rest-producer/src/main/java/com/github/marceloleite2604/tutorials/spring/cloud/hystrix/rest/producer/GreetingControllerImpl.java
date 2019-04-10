package com.github.marceloleite2604.tutorials.spring.cloud.hystrix.rest.producer;

import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingControllerImpl implements GreetingController {

	@Override
	public String greeting(@PathVariable("username") String username) {
		try {
			Thread.sleep((long)(new Random().nextDouble()*2000.0));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.format("Hello %s!%n", username);
	}
}