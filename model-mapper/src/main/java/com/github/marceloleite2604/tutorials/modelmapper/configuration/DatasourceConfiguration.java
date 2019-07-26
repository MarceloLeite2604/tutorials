package com.github.marceloleite2604.tutorials.modelmapper.configuration;


import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marceloleite2604.tutorials.modelmapper.properties.DatabaseProperties;
import com.github.marceloleite2604.tutorials.modelmapper.util.EncryptionUtil;

@Configuration
public class DatasourceConfiguration {

	private static final String PARAMETER_FORMAT_TEMPLATE = "%s=%s&";

	@SuppressWarnings("squid:S2095")
	@Bean(BeanNames.DATA_SOURCE)
	public DataSource criarDataSource(DatabaseProperties databaseProperties,
			EncryptionUtil encryptionUtil) {

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(databaseProperties.getDriverClassName());
		basicDataSource.setUrl(elaborateUrl(databaseProperties));
		basicDataSource.setUsername(databaseProperties.getUsername());
		basicDataSource.setPassword(encryptionUtil.decrypt(databaseProperties.getPassword()));
		basicDataSource.setMaxIdle(databaseProperties.getConnections());
		basicDataSource.setMaxTotal(databaseProperties.getConnections());

		return basicDataSource;
	}

	private String elaborateUrl(DatabaseProperties databaseProperties) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(databaseProperties.getUrl());

		addProperties(databaseProperties, stringBuilder);

		return stringBuilder.toString();
	}

	private void addProperties(DatabaseProperties databaseProperties, StringBuilder stringBuilder) {

		Optional<Map<String, String>> optionalOtherProperties = Optional
				.ofNullable(databaseProperties.getOtherConnectionProperties());

		if (optionalOtherProperties.isPresent()) {
			stringBuilder.append("?");
			for (Entry<String, String> otherPropertyEntry : optionalOtherProperties.get()
					.entrySet()) {
				stringBuilder.append(String.format(PARAMETER_FORMAT_TEMPLATE,
						otherPropertyEntry.getKey(), otherPropertyEntry.getValue()));

			}
		}
	}
}
