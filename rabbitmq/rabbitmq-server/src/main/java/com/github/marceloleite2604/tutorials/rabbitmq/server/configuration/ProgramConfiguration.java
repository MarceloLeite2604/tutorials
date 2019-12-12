package com.github.marceloleite2604.tutorials.rabbitmq.server.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marceloleite2604.tutorials.rabbitmq.server.properties.RabbitMqProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProgramConfiguration {

	@Bean(BeanNames.CONNECTION_FACTORY)
	public ConnectionFactory createConnectionFactory(RabbitMqProperties rabbitMqProperties) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(rabbitMqProperties.getHost());
		connectionFactory.setPort(rabbitMqProperties.getPort());
		connectionFactory.setUsername(rabbitMqProperties.getUsername());
		connectionFactory.setPassword(rabbitMqProperties.getPassword());

		return connectionFactory;
	}

	@Bean(BeanNames.CONNECTION)
	public Connection createConnection(ConnectionFactory connectionFactory)
			throws IOException, TimeoutException {
		return connectionFactory.newConnection();
	}

	@SuppressWarnings("squid:S2095")
	@Bean(BeanNames.CHANNEL)
	public Channel createChannel(Connection connection, RabbitMqProperties rabbitMqProperties)
			throws IOException {
		return connection.createChannel();
	}

	@Bean(BeanNames.OBJECT_MAPPER)
	public ObjectMapper createObjectMapper() {
		return new ObjectMapper();
	}
}
