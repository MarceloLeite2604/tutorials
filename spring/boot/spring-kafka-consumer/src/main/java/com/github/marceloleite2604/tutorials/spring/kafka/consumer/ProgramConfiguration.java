package com.github.marceloleite2604.tutorials.spring.kafka.consumer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Named;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties.AckMode;

@Configuration
public class ProgramConfiguration {

	@ConfigurationProperties("kafka")
	@Bean(NomesBeans.KAFKA_PROPERTIES)
	public Properties criarKafkaProperties() {
		return new Properties();
	}

	@Bean(NomesBeans.KAFKA_CONSUMER_FACTORY)
	public ConsumerFactory<Object, Object> createConsumerFactory(
			@Named(NomesBeans.KAFKA_PROPERTIES) Properties kafkaProperties) {
		Map<String, Object> propertiesMap = new HashMap<>();
		propertiesMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				kafkaProperties.get(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG));
		propertiesMap.put(ConsumerConfig.GROUP_ID_CONFIG,
				kafkaProperties.get(ConsumerConfig.GROUP_ID_CONFIG));
		propertiesMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		propertiesMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		propertiesMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

		return new DefaultKafkaConsumerFactory<>(propertiesMap);
	}

	@Bean(NomesBeans.CONCURRENT_KAFKA_LISTENER_CONTAINER_FACTORY)
	public ConcurrentKafkaListenerContainerFactory<String, String> createConcurrentKafkaListenerContainerFactory(
			ConsumerFactory<Object, Object> consumerFactory) {

		ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
		concurrentKafkaListenerContainerFactory.getContainerProperties()
				.setAckMode(AckMode.MANUAL_IMMEDIATE);
		return concurrentKafkaListenerContainerFactory;
	}
}
