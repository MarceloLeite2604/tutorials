package com.github.marceloleite2604.tutorials.spring.cloud.remote.properties.server;

import java.util.Optional;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProgramConfiguration {

	@Value("${spring.application.name}")
	private String applicationName;

	@Bean
	public CachingConnectionFactory criarCachingConnectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(
				"localhost");
		if (Optional.ofNullable(applicationName)
				.isPresent()) {
			cachingConnectionFactory.setConnectionNameStrategy(f -> applicationName);
		}
		return cachingConnectionFactory;
	}
}
