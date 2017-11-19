package br.com.casadocodigo.loja.conf;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Como o nosso ambiente de produção é executado no Heroku, o banco de dados
 * utilizado é Postgre. Logo, temos que alterar as configurações de comunicação
 * do JPA para utilizar este banco e associá-las com um novo profile de
 * produção.
 */
@Profile("prod")
public class JPAProductionConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	private Properties additionalProperties() {
		/* Conjunto de propriedades a ser definido no hibernate para o JPA. */
		Properties jpaProperties = new Properties();

		/* Dialeto utilizado para conversar com o banco de dados. */
		jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

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
	public DataSource dataSource() throws URISyntaxException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");

		/*
		 * Quando o sistema é executado dentro do Heroku, no ambiente existe uma
		 * variável "DATABSE_URL" que contém as informações de acesso do banco
		 * de dados. Estas informações estão no formato
		 * "usuario:senha@host:port/path". Logo, para podermos obter estas
		 * informações corretamente, iremos colocá-la em um objeto URI.
		 */

		URI dbUrl = new URI(environment.getProperty("DATABASE_URL"));

		dataSource.setUrl("jdbc:postgresql://" + dbUrl.getHost() + ":" + dbUrl.getPort() + dbUrl.getPath());
		dataSource.setUsername(dbUrl.getUserInfo().split(":")[0]);
		dataSource.setPassword(dbUrl.getUserInfo().split(":")[1]);

		return dataSource;
	}
}
