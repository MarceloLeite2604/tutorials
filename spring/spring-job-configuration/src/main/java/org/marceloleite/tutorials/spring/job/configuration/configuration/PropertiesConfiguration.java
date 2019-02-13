package org.marceloleite.tutorials.spring.job.configuration.configuration;

import java.util.Properties;

import javax.inject.Named;

import org.marceloleite.tutorials.spring.job.configuration.job.MeuJobPropriedade;
import org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.contexto.ObterClientesContextoPropriedade;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes.contexto.PersistirClientesContextoPropriedade;
import org.marceloleite.tutorials.spring.job.configuration.util.PropriedadeUtil;
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

	@Bean("meuJobProperties")
	@ConfigurationProperties("meu-job")
	public Properties criarMeuJobProperties() {
		return new Properties();
	}

	@Bean("obterClientesProperties")
	@ConfigurationProperties("meu-job.obter-clientes")
	public Properties criarObterClientesProperties(
			@Named("meuJobProperties") Properties meuJobProperties) {
		Properties obterClientesProperties = new Properties();

		PropriedadeUtil.copiar(meuJobProperties, MeuJobPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS,
				obterClientesProperties,
				ObterClientesContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS);

		PropriedadeUtil.copiar(meuJobProperties, MeuJobPropriedade.TOTAL_DE_REGISTROS,
				obterClientesProperties, ObterClientesContextoPropriedade.TOTAL_DE_REGISTROS);

		return obterClientesProperties;
	}

	@Bean("persistirClientesProperties")
	@ConfigurationProperties("meu-job.persistir-clientes")
	public Properties criarPersistirClientesProperties(
			@Named("meuJobProperties") Properties meuJobProperties) {
		Properties persistirClientesProperties = new Properties();

		PropriedadeUtil.copiar(meuJobProperties, MeuJobPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS,
				persistirClientesProperties,
				PersistirClientesContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS);

		PropriedadeUtil.copiar(meuJobProperties, MeuJobPropriedade.TOTAL_DE_REGISTROS,
				persistirClientesProperties,
				PersistirClientesContextoPropriedade.TOTAL_DE_REGISTROS);

		return persistirClientesProperties;
	}

}
