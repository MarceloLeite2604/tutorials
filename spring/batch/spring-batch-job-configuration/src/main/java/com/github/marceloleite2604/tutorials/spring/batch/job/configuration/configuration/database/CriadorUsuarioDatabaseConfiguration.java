package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.configuration.database;

import java.util.HashMap;

import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.diversos.NomesBeans;

@Configuration
@EnableJpaRepositories(
		basePackages = CriadorUsuarioDatabaseConfiguration.BASE_PACKAGES,
		entityManagerFactoryRef = NomesBeans.PROGRAMA_ENTITY_MANAGER_FACTORY_BEAN,
		transactionManagerRef = NomesBeans.PROGRAMA_TRANSACTION_MANAGER)
public class CriadorUsuarioDatabaseConfiguration {

	public static final String BASE_PACKAGES = "com.github.marceloleite2604.tutorials.spring.batch.job.configuration";

	@Bean(NomesBeans.PROGRAMA_ENTITY_MANAGER_FACTORY_BEAN)
	public LocalContainerEntityManagerFactoryBean criarProgramaEntityManagerFactoryBean(
			@Named(NomesBeans.PROGRAMA_DATA_SOURCE) DataSource programaDataSource,
			Environment environment) {

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(programaDataSource);
		entityManagerFactoryBean.setPackagesToScan(BASE_PACKAGES);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		entityManagerFactoryBean.setJpaPropertyMap(properties);

		return entityManagerFactoryBean;
	}

	@Bean(NomesBeans.PROGRAMA_TRANSACTION_MANAGER)
	public PlatformTransactionManager criarProgramaTransactionManager(
			@Named(NomesBeans.PROGRAMA_ENTITY_MANAGER_FACTORY_BEAN) LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {

		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
		return jpaTransactionManager;
	}
}
