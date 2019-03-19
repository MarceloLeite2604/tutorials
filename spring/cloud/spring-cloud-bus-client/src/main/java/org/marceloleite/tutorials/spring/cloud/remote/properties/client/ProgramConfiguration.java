package org.marceloleite.tutorials.spring.cloud.remote.properties.client;

import java.util.Optional;
import java.util.Properties;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProgramConfiguration {

	@Value("${spring.application.name}")
	private String applicationName;

	@ConfigurationProperties("program")
	@Bean("programProperties")
	public Properties criarProgramProperties() {
		return new Properties();
	}

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
