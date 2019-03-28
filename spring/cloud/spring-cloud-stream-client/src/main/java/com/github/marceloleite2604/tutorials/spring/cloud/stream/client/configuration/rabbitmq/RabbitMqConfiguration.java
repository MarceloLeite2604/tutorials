package com.github.marceloleite2604.tutorials.spring.cloud.stream.client.configuration.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
@Profile("rabbitmq")
@EnableRabbit
public class RabbitMqConfiguration {

	@Bean
	public MessageConverter criarMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public SmartInitializingSingleton reconfigureConnectionFactory(
			AbstractConnectionFactory connectionFactory, Environment environment) {
		return () -> connectionFactory.setConnectionNameStrategy(
				f -> environment.getProperty("spring.application.name", "default-client-name"));
	}

}
