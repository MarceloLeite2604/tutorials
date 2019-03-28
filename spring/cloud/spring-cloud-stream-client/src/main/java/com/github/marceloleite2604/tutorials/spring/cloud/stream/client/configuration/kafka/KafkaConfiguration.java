package com.github.marceloleite2604.tutorials.spring.cloud.stream.client.configuration.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.support.converter.MessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
@EnableKafka
@Profile("kafka")
public class KafkaConfiguration {

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> criarConcurrentKafkaListenerContainerFactory(
			ConsumerFactory<Object, Object> consumerFactory) {

		ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
		concurrentKafkaListenerContainerFactory.getContainerProperties()
				.setAckMode(AckMode.MANUAL_IMMEDIATE);
		return concurrentKafkaListenerContainerFactory;
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
	    return new StringJsonMessageConverter();
	}

}
