package org.marceloleite.tutorials.spring.kafka.consumer;

public final class NomesBeans {

	private NomesBeans() {
		// Construtor privado para evitar a criação de objetos desta classe.
	}

	public static final String CONCURRENT_KAFKA_LISTENER_CONTAINER_FACTORY = "concurrentKafkaListenerContainerFactory";
	public static final String KAFKA_CONSUMER_FACTORY = "kafkaConsumerFactory";
}
