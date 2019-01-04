package org.marceloleite.tutorials.spring.batch.partitioner.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfiguration {

	private static final String DATABASE_FILE = "hsqldb/database";

	/*
	 * @Bean public DataSource criarDataSource() throws IOException {
	 * deleteDatabase(); DriverManagerDataSource driverManagerDataSource = new
	 * DriverManagerDataSource();
	 * driverManagerDataSource.setDriverClassName("org.sqlite.JDBC");
	 * driverManagerDataSource.setUrl("jdbc:sqlite:repository.sqlite");
	 * driverManagerDataSource.setUsername("");
	 * driverManagerDataSource.setPassword("");
	 * 
	 * return driverManagerDataSource; }
	 */
	
	@Bean
	public DataSource criarDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:hsqldb:file:"+DATABASE_FILE+";hsqldb.log_data=false;hsqldb.nio_max_size=4096");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
        return dataSource;
}

	private void deleteDatabase() throws IOException {
		if (Paths.get(DATABASE_FILE)
				.toFile()
				.exists()) {
			Files.delete(Paths.get(DATABASE_FILE));
		}
	}
}
