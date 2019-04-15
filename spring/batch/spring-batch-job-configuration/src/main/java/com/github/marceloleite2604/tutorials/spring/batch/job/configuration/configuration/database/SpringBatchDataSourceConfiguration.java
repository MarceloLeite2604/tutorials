package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.configuration.database;

import java.util.Properties;

import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.diversos.NomesBeans;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util.CriptografiaUtil;

@Configuration
public class SpringBatchDataSourceConfiguration {

	private static final int MAXIMO_CONEXOES_EM_AGUARDO = 4;

	private static final int TOTAL_CONEXOES = 6;

	@Bean(NomesBeans.SPRING_BATCH_DATA_SOURCE)
	@Primary
	public DataSource criarSpringBatchDataSource(
			@Named(NomesBeans.SPRING_BATCH_DATA_SOURCE_PROPERTIES) Properties springBatchDataSourceProperties,
			CriptografiaUtil criptografiaUtil) {
		String usuario = springBatchDataSourceProperties.getProperty("username");
		String senha = criptografiaUtil
				.decriptografar(springBatchDataSourceProperties.getProperty("senha"));
		String url = springBatchDataSourceProperties.getProperty("url");

		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, usuario,
				senha);

		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
				connectionFactory, null);

		GenericObjectPool<PoolableConnection> genericObjectPool = new GenericObjectPool<>(
				poolableConnectionFactory);

		genericObjectPool.setMaxIdle(MAXIMO_CONEXOES_EM_AGUARDO);
		genericObjectPool.setMaxTotal(TOTAL_CONEXOES);

		poolableConnectionFactory.setPool(genericObjectPool);
		return new PoolingDataSource<>(genericObjectPool);
	}

	@Bean(NomesBeans.PROGRAMA_DATA_SOURCE)
	public DataSource criarProgramaDataSource(
			@Named(NomesBeans.PROGRAMA_DATA_SOURCE_PROPERTIES) Properties programaDataSourceProperties,
			CriptografiaUtil criptografia) {

		String usuario = programaDataSourceProperties.getProperty("username");
		String senha = criptografia
				.decriptografar(programaDataSourceProperties.getProperty("senha"));
		String url = programaDataSourceProperties.getProperty("url");

		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, usuario,
				senha);

		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
				connectionFactory, null);

		GenericObjectPool<PoolableConnection> genericObjectPool = new GenericObjectPool<>(
				poolableConnectionFactory);

		genericObjectPool.setMaxIdle(MAXIMO_CONEXOES_EM_AGUARDO);
		genericObjectPool.setMaxTotal(TOTAL_CONEXOES);

		poolableConnectionFactory.setPool(genericObjectPool);
		return new PoolingDataSource<>(genericObjectPool);
	}
}