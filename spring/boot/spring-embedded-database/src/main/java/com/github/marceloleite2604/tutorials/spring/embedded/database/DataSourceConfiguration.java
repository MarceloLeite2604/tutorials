package com.github.marceloleite2604.tutorials.spring.embedded.database;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataSourceConfiguration {

	private static final Logger LOGGER = org.slf4j.LoggerFactory
			.getLogger(DataSourceConfiguration.class);

	@Bean
	@Profile("hsql")
	public EmbeddedDatabaseType criarEmbeddedDatabaseTypeHSQL() {
		return EmbeddedDatabaseType.HSQL;
	}

	@Bean
	@Profile("h2")
	public EmbeddedDatabaseType criarEmbeddedDatabaseTypeH2() {
		return EmbeddedDatabaseType.H2;
	}

	@Bean
	@Profile("derby")
	public EmbeddedDatabaseType criarEmbeddedDatabaseTypeDerby() {
		return EmbeddedDatabaseType.DERBY;
	}

	@Bean
	public DataSource criarDataSource(EmbeddedDatabaseType embeddedDatabaseType) {
		LOGGER.info("Criando um banco de dados do tipo {}.", embeddedDatabaseType);
		return new EmbeddedDatabaseBuilder().setType(embeddedDatabaseType)
				.build();
	}
}
