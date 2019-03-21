package org.marceloleite.tutorials.spring.kafka.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class ProgramConfiguration {

	@Value("${kafka.bootstrapServers}")
	private String bootstrapServers;

	@Value("${kafka.groupId}")
	private String groupId;

	@Bean(NomesBeans.KAFKA_CONSUMER_FACTORY)
	public ConsumerFactory<Object, Object> createConsumerFactory() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "3000");
		
		return new DefaultKafkaConsumerFactory<>(properties);
	}

	@Bean(NomesBeans.CONCURRENT_KAFKA_LISTENER_CONTAINER_FACTORY)
	public ConcurrentKafkaListenerContainerFactory<String, String> createConcurrentKafkaListenerContainerFactory(
			ConsumerFactory<Object, Object> consumerFactory) {

		ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
		return concurrentKafkaListenerContainerFactory;
	}
}
