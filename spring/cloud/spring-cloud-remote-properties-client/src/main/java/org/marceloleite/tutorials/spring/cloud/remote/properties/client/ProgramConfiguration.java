package org.marceloleite.tutorials.spring.cloud.remote.properties.client;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProgramConfiguration {

	@ConfigurationProperties("program")
	@Bean("programProperties")
	public Properties criarProgramProperties() {
		return new Properties();
	}
}
