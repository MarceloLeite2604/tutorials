package com.github.marceloleite2604.tutorials.spring.eureka.feign.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@EnableFeignClients
@Controller
public class Main {
	
     public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    
}