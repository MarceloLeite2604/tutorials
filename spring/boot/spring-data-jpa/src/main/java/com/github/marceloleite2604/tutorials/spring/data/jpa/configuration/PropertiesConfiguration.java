package com.github.marceloleite2604.tutorials.spring.data.jpa.configuration;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marceloleite2604.tutorials.spring.data.jpa.DefinicoesPrograma;

@Configuration
public class PropertiesConfiguration {
	
	@Bean(NomesBeans.PROPERTIES_BANCO_DADOS)
	@ConfigurationProperties(DefinicoesPrograma.PREFIXO_PROPRIEDADES_BANCO_DADOS)
	public Properties criarPropertiesBancoDados() {
		return new Properties();
	}
	
	@Bean(NomesBeans.PROPERTIES_CRIPTOGRAFIA)
	@ConfigurationProperties(DefinicoesPrograma.PREFIXO_PROPRIEDADES_CRIPTOGRAFIA)
	public Properties criarPropertiesCriptografia() {
		return new Properties();
	}

}
