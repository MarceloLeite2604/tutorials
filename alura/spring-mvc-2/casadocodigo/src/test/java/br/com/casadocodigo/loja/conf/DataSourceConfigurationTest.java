package br.com.casadocodigo.loja.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceConfigurationTest {

	/*
	 * O método abaixo só será utilizado quando o profile "test" estiver sendo
	 * utilizado. Caso contrário, será utilizado o método
	 * "JPAConfiguration.dataSource()" para definir o data source utilizado no
	 * projeto.
	 */
	@Bean
	@Profile("test")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo_test");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("u42krw");

		return dataSource;
	}
}
