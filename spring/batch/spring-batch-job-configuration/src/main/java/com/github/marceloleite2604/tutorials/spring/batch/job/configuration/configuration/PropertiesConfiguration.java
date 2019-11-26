package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.configuration;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties.BancoProperties;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties.CaminhosProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
public class PropertiesConfiguration {

	@Bean(NomesBeans.SPRING_BATCH_BANCO_PROPERTIES)
	@ConfigurationProperties(CaminhosProperties.BANCO_SPRING_BATCH)
	@Validated
	public BancoProperties criarSpringBatchBancoPropriedades() {
		return new BancoProperties();
	}

	@Bean(NomesBeans.PROGRAMA_BANCO_PROPERTIES)
	@ConfigurationProperties(CaminhosProperties.BANCO_PROGRAMA)
	@Validated
	public BancoProperties criarProgramaBancoPropriedades() {
		return new BancoProperties();
	}

}
