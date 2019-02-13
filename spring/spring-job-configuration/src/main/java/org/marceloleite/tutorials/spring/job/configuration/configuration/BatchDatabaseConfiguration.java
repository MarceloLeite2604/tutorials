package org.marceloleite.tutorials.spring.job.configuration.configuration;

import java.util.Properties;

import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.marceloleite.tutorials.spring.job.configuration.util.Criptografia;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BatchDatabaseConfiguration {

	private static final int MAXIMO_CONEXOES_EM_AGUARDO = 4;

	private static final int TOTAL_CONEXOES = 6;
	
	@Bean("batchDataSource")
	@Primary
	public DataSource criarBatchDataSource(
			@Named("batchDataSourceProperties") Properties properties,
			Criptografia criptografia) {
		String usuario = properties.getProperty("username");
		String senha = criptografia.decriptografar(properties.getProperty("senha"));
		String url = properties.getProperty("url");

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
	
	@Bean
	public DataSource criarProgramaDataSource(
			@Named("programaDataSourceProperties") Properties properties,
			Criptografia criptografia) {

		String usuario = properties.getProperty("username");
		String senha = criptografia.decriptografar(properties.getProperty("senha"));
		String url = properties.getProperty("url");

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
