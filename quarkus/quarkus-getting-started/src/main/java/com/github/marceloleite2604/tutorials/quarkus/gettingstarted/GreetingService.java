package com.github.marceloleite2604.tutorials.quarkus.gettingstarted;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

	public String greeting(String name) {
		return "hello " + name;
	}
}
