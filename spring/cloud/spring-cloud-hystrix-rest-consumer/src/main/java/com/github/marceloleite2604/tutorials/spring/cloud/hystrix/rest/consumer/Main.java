package com.github.marceloleite2604.tutorials.spring.cloud.hystrix.rest.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}