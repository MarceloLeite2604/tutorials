package com.github.marceloleite2604.tutorials.spring.job.configuration.configuration;

import java.util.Properties;

import javax.inject.Named;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marceloleite2604.tutorials.spring.job.configuration.diversos.NomesBeans;
import com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.CaminhosPropriedades;
import com.github.marceloleite2604.tutorials.spring.job.configuration.util.PropriedadeUtil;

@Configuration
public class PropertiesConfiguration {

	@Bean(NomesBeans.ENCRYPTOR_DECRYPTOR_PROPERTIES)
	@ConfigurationProperties(CaminhosPropriedades.CRIPTOGRAFIA)
	public Properties criarEncryptorDecryptorProperties() {
		return new Properties();
	}

	@Bean(NomesBeans.SPRING_BATCH_DATA_SOURCE_PROPERTIES)
	@ConfigurationProperties(CaminhosPropriedades.BANCO_SPRING_BATCH)
	public Properties criarBatchDataSourceProperties() {
		return new Properties();
	}

	@Bean(NomesBeans.PROGRAMA_DATA_SOURCE_PROPERTIES)
	@ConfigurationProperties(CaminhosPropriedades.BANCO_PROGRAMA)
	public Properties criarProgramaDataSourceProperties() {
		return new Properties();
	}

	@Bean(NomesBeans.CRIADOR_USUARIOS_PROPERTIES)
	@ConfigurationProperties(CaminhosPropriedades.CRIADOR_USUARIOS)
	public Properties criarCriadorUsuariosProperties() {
		return new Properties();
	}

	@Bean(NomesBeans.AQUISICAO_USUARIOS_PROPERTIES)
	@ConfigurationProperties(CaminhosPropriedades.AQUISICAO_USUARIOS)
	public Properties criarAquisicaoUsuariosProperties(
			@Named(NomesBeans.CRIADOR_USUARIOS_PROPERTIES) Properties criadorUsuariosProperties) {
		
		return PropriedadeUtil.clonar(criadorUsuariosProperties);
	}

	@Bean(NomesBeans.PERSISTENCIA_USUARIOS_PROPERTIES)
	@ConfigurationProperties(CaminhosPropriedades.PERSISTENCIA_USUARIOS)
	public Properties criarPersistenciaUsuariosProperties(
			@Named(NomesBeans.CRIADOR_USUARIOS_PROPERTIES) Properties criadorUsuariosProperties) {
		
		return PropriedadeUtil.clonar(criadorUsuariosProperties);
	}

}
