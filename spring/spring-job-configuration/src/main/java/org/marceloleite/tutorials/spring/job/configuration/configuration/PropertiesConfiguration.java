package org.marceloleite.tutorials.spring.job.configuration.configuration;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {

	@Bean("encryptorDecryptorProperties")
	@ConfigurationProperties("criptografia")
	public Properties criarEncryptorDecryptorProperties() {
		return new Properties();
	}
	
	@Bean("batchDataSourceProperties")
	@ConfigurationProperties("banco.batch")
	public Properties criarBatchDataSourceProperties() {
		return new Properties();
	}
	
	@Bean("programaDataSourceProperties")
	@ConfigurationProperties("banco.programa")
	public Properties criarProgramaDataSourceProperties() {
		return new Properties();
	}
}
