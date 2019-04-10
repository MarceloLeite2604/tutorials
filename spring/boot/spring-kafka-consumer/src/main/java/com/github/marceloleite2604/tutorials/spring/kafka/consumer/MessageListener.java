package com.github.marceloleite2604.tutorials.spring.kafka.consumer;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

	@Inject
	@Named(NomesBeans.KAFKA_PROPERTIES)
	private Properties kafkaProperties;

	private AtomicLong totalMensagens = new AtomicLong(0L);

	public AtomicLong getTotalMensagens() {
		return totalMensagens;
	}

	@KafkaListener(
			topics = "${kafka.topic}",
			groupId = "${kafka.group.id}",
			containerFactory = NomesBeans.CONCURRENT_KAFKA_LISTENER_CONTAINER_FACTORY)
	public void receberMensagem(@Payload String message, Acknowledgment acknowledgment) {

		LOGGER.info("Recebida a seguinte mensagem no grupo \"{}\": {}",
				kafkaProperties.getProperty("group.id"), message);

		totalMensagens.incrementAndGet();
		acknowledgment.acknowledge();
	}
}