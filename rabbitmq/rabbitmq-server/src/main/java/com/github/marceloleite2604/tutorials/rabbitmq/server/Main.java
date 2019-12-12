package com.github.marceloleite2604.tutorials.rabbitmq.server;

import com.github.marceloleite2604.tutorials.rabbitmq.server.properties.RabbitMqProperties;
import com.github.marceloleite2604.tutorials.rabbitmq.server.properties.RabbitMqServerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ RabbitMqServerProperties.class, RabbitMqProperties.class })
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
