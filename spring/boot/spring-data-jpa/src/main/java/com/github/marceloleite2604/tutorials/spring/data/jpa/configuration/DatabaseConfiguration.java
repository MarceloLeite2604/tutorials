package com.github.marceloleite2604.tutorials.spring.data.jpa.configuration;

import java.util.Properties;

import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marceloleite2604.properties.PropertyUtil;
import com.github.marceloleite2604.tutorials.spring.data.jpa.propriedade.BancoDadosProperty;
import com.github.marceloleite2604.tutorials.spring.data.jpa.util.CriptografiaUtil;

@Configuration
public class DatabaseConfiguration {

	@Bean(NomesBeans.DATA_SOURCE)
	public DataSource criarDataSource(
			@Named(NomesBeans.PROPERTIES_BANCO_DADOS) Properties propertiesBancoDados,
			CriptografiaUtil criptografiaUtil) {

		String driverClassName = PropertyUtil.retrieve(BancoDadosProperty.DRIVER_CLASS_NAME,
				propertiesBancoDados);

		String url = PropertyUtil.retrieve(BancoDadosProperty.URL, propertiesBancoDados);

		String usuario = PropertyUtil.retrieve(BancoDadosProperty.USUARIO, propertiesBancoDados);

		String senha = criptografiaUtil.decriptografar(
				PropertyUtil.retrieve(BancoDadosProperty.SENHA, propertiesBancoDados));

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driverClassName);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(usuario);
		basicDataSource.setPassword(senha);

		return basicDataSource;
	}
}
