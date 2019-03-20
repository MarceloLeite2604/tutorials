package org.marceloleite.tutorials.spring.cloud.zuul.rest.api;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FooController {

	private Random random = new Random();

	@GetMapping(value = "/foos/{id}")
	@ResponseBody
	public Foo findById(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getHeader("Test") != null) {
            response.addHeader("Test", request.getHeader("Test"));
        }
		return new Foo(randomNumeric(2), randomAlphabetic(4));
	}

	private String randomAlphabetic(int size) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int counter = 0; counter < size; counter++) {
			stringBuilder.append((char) (random.nextInt(26) + 'a'));
		}
		return stringBuilder.toString();
	}

	private long randomNumeric(int size) {
		return (long) (Math.random() * Math.pow(10.0, (double)size));
	}
}