package com.github.marceloleite2604.tutorials.spring.cloud.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
public class Main 
{
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
