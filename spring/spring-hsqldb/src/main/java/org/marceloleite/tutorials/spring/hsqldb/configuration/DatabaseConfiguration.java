package org.marceloleite.tutorials.spring.hsqldb.configuration;

import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.marceloleite.tutorials.spring.hsqldb.util.BancoDadosUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

	@Bean("dataSources")
	public List<DataSource> dataSource() {
		List<DataSource> dataSources = new LinkedList<>();
		for (int contador = 0; contador < BancoDadosUtil.TOTAL_BANCO_DADOS; contador++) {
			dataSources.add(criarDataSource(contador));
		}

		return dataSources;
	}

	private BasicDataSource criarDataSource(int instancia) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:hsqldb:file:hsqldb/database" + instancia +";hsqldb.log_data=false;hsqldb.nio_max_size=4096");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		dataSource.setMinIdle(5);
		dataSource.setMaxIdle(10);
		dataSource.setMaxOpenPreparedStatements(100);
		return dataSource;
	}
}