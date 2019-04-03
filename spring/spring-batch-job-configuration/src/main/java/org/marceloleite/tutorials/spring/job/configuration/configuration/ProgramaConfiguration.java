package org.marceloleite.tutorials.spring.job.configuration.configuration;

import java.util.Properties;

import javax.inject.Named;

import org.marceloleite.encrypt.EncryptorDecryptor;
import org.marceloleite.tutorials.spring.job.configuration.diversos.NomesBeans;
import org.marceloleite.tutorials.spring.job.configuration.propriedade.CriptografiaPropriedade;
import org.marceloleite.tutorials.spring.job.configuration.util.PropriedadeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProgramaConfiguration {

	@Bean
	public EncryptorDecryptor criarEncryptorDecryptor(
			@Named(NomesBeans.ENCRYPTOR_DECRYPTOR_PROPERTIES) Properties encryptorDecryptorProperties) {
		return EncryptorDecryptor.builder()
				.cryptographicAlgorythm(
						PropriedadeUtil.obter(CriptografiaPropriedade.ALGORITMO_CRIPTOGRAFIA,
								encryptorDecryptorProperties))
				.feedbackMode(PropriedadeUtil.obter(CriptografiaPropriedade.MODO_FEEDBACK,
						encryptorDecryptorProperties))
				.paddingScheme(PropriedadeUtil.obter(CriptografiaPropriedade.MODO_PREENCHIMENTO,
						encryptorDecryptorProperties))
				.build();
	}

	@Bean
	public RestTemplate criarRestTemplate() {
		return new RestTemplate();
	}
}
