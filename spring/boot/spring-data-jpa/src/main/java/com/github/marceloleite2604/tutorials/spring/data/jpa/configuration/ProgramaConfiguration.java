package com.github.marceloleite2604.tutorials.spring.data.jpa.configuration;

import java.util.Properties;

import javax.inject.Named;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marceloleite2604.encryptor.EncryptorDecryptor;
import com.github.marceloleite2604.properties.PropertyUtil;
import com.github.marceloleite2604.tutorials.spring.data.jpa.propriedade.CriptografiaProperty;

@Configuration
public class ProgramaConfiguration {

	@Bean
	public EncryptorDecryptor criarEncryptorDecryptor(
			@Named(NomesBeans.PROPERTIES_CRIPTOGRAFIA) Properties encryptorDecryptorProperties) {
		
		String algoritmo = PropertyUtil.retrieve(CriptografiaProperty.ALGORITMO,
				encryptorDecryptorProperties);
		
		String modoFeedback = PropertyUtil.retrieve(CriptografiaProperty.MODO_FEEDBACK,
				encryptorDecryptorProperties);
		
		String modoPreenchimento = PropertyUtil.retrieve(CriptografiaProperty.MODO_PREENCHIMENTO,
				encryptorDecryptorProperties);
		
		return EncryptorDecryptor.builder()
				.cryptographicAlgorythm(algoritmo)
				.feedbackMode(modoFeedback)
				.paddingScheme(modoPreenchimento)
				.build();
	}

//	@Bean
//	public RestTemplate criarRestTemplate() {
//		return new RestTemplate();
//	}
}
