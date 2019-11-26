package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.configuration;

import com.github.marceloleite2604.sled.Sled;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties.CriptografiaProperties;
import com.github.marceloleite2604.util.file.FileUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProgramaConfiguration {
	
	@Bean(NomesBeans.FILE_UTIL)
	public FileUtil criarFileUtil() {
		return new FileUtil();
	}

	@Bean(NomesBeans.SLED)
	public Sled criarSled(
			CriptografiaProperties criptografiaPropriedades) {
		return Sled.builder()
				.cryptographicAlgorithm(criptografiaPropriedades.getAlgoritmoCriptografia())
				.feedbackMode(criptografiaPropriedades.getModoFeedback())
				.paddingScheme(criptografiaPropriedades.getModoPreenchimento())
				.build();
	}

	@Bean(NomesBeans.REST_TEMPLATE)
	public RestTemplate criarRestTemplate() {
		return new RestTemplate();
	}
}
