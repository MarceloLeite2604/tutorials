package org.marceloleite.tutorials.spring.job.configuration.configuration;

import java.util.HashMap;
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
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "org.marceloleite.tutorials.spring.job.configuration", entityManagerFactoryRef = "meuJobEntityManager", transactionManagerRef = "meuJobTransactionManager")
public class MeuJobDatabaseConfiguration {

	private static final int MAXIMO_CONEXOES_EM_AGUARDO = 4;

	private static final int TOTAL_CONEXOES = 6;

	@Bean("meuJobDataSource")
	public DataSource criarMeuJobDataSource(
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

	@Bean("meuJobEntityManager")
	public LocalContainerEntityManagerFactoryBean criarMeuJobEntityManager(
			@Named("meuJobDataSource") DataSource meuJobDataSource, Environment environment) {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(meuJobDataSource);
		entityManager.setPackagesToScan(
				new String[] { "org.marceloleite.tutorials.spring.job.configuration" });

		entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		entityManager.setJpaPropertyMap(properties);

		return entityManager;
	}

	@Bean("meuJobTransactionManager")
	public PlatformTransactionManager userTransactionManager(
			@Named("meuJobEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {

		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
		return jpaTransactionManager;
	}
}
