package br.com.alura.listavip;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * Explica que a aplicação será executada através do spring boot.
 */
@SpringBootApplication
public class Configuracao {

	@RequestMapping("/")
	@ResponseBody
	String ola() {
		return "Olá. Está tudo funcionando";
	}

	public static void main(String[] args) {
		/*
		 * Informa para o spring que ele deve executar a classe informada
		 * passando os mesmos argumentos de execução.
		 */
		/*
		 * Por padrão, o Spring utiliza o Tomcat para executar as aplicações.
		 * Entretanto é possível trocar o servidor utilizado.
		 */
		SpringApplication.run(Configuracao.class, args);
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/listavip");
		dataSource.setUsername("root");
		dataSource.setPassword("marcelo");
		return dataSource;
	}

}
