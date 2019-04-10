package com.github.marceloleite2604.tutorials.spring.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Named;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class ProgramConfiguration {

	@ConfigurationProperties("kafka")
	@Bean(NomesBeans.KAFKA_PROPERTIES)
	public Properties criarKafkaProperties() {
		return new Properties();
	}

	@Bean
	public KafkaAdmin createKafkaAdmin(
			@Named(NomesBeans.KAFKA_PROPERTIES) Properties kafkaProperties) {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
				kafkaProperties.get(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG));
		return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic createTestTopic(
			@Named(NomesBeans.KAFKA_PROPERTIES) Properties kafkaProperties) {
		return new NewTopic(kafkaProperties.getProperty("topic"), 1, (short) 1);
	}

	@Bean
	public ProducerFactory<String, String> createProducerFactory(
			@Named(NomesBeans.KAFKA_PROPERTIES) Properties kafkaProperties) {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				kafkaProperties.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(properties);
	}

	@Bean
	public KafkaTemplate<String, String> createKafkaTemplate(
			ProducerFactory<String, String> producerFactory) {
		return new KafkaTemplate<>(producerFactory);
	}
}
