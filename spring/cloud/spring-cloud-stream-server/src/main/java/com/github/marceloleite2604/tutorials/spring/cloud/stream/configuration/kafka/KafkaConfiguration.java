package com.github.marceloleite2604.tutorials.spring.cloud.stream.configuration.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.converter.MessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
@EnableKafka
@Profile("kafka")
public class KafkaConfiguration {

	@Bean
	public MessageConverter jsonMessageConverter() {
	    return new StringJsonMessageConverter();
	}

}
