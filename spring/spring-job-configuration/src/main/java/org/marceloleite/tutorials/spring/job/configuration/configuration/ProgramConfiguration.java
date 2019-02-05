package org.marceloleite.tutorials.spring.job.configuration.configuration;

import java.util.Properties;

import javax.inject.Named;

import org.marceloleite.encrypt.EncryptorDecryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProgramConfiguration {

	@Bean
	public EncryptorDecryptor criarEncryptorDecryptor(
			@Named("encryptorDecryptorProperties") Properties properties) {
		return EncryptorDecryptor.builder()
				.cryptographicAlgorythm(properties.getProperty("algoritmoCriptografia", "DESede"))
				.feedbackMode(properties.getProperty("modoFeedback", "CBC"))
				.paddingScheme(properties.getProperty("modoPreenchimento", "PKCS5Padding"))
				.build();
	}

	@Bean
	public RestTemplate criarRestTemplate() {
		return new RestTemplate();
	}
}
