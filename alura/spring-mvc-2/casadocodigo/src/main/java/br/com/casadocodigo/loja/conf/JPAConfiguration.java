package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		
		/*
		 * Package a ser analisada para obter os classes que compõem nossas
		 * entidades.
		 */
		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		factoryBean.setJpaVendorAdapter(vendorAdapter);

		factoryBean.setDataSource(dataSource);

		factoryBean.setJpaProperties(additionalProperties());		

		return factoryBean;
	}

	private Properties additionalProperties() {
		/* Conjunto de propriedades a ser definido no hibernate para o JPA. */
		Properties jpaProperties = new Properties();

		/* Dialeto utilizado para conversar com o banco de dados. */
		jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

		/* Apresenta o SQL elaborado. */
		jpaProperties.setProperty("hibernate.show_sql", "true");

		/*
		 * O hibernate se encarrega de atualizar o modelo de banco toda vez que
		 * houver uma alteração na estrutura dos dados.
		 */
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		return jpaProperties;
	}

	@Bean
	@Profile("dev")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("u42krw");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
