package com.github.marceloleite2604.tutorials.spring.security.database.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marceloleite2604.tutorials.spring.security.database.properties.ProgramaBancoProperties;

@Configuration
public class DatasourceConfiguration {

	@Bean(NomesBeans.DATA_SOURCE)
	public DataSource criarDataSource(ProgramaBancoProperties programaBancoProperties) {

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(programaBancoProperties.getDriverClassName());
		basicDataSource.setUrl(programaBancoProperties.getUrl());
		basicDataSource.setUsername(programaBancoProperties.getUsuario());
		basicDataSource.setPassword(programaBancoProperties.getSenha());
		basicDataSource.setMaxIdle(programaBancoProperties.getQuantidadeDeConexoes());

		return basicDataSource;
	}
}
