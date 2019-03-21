package org.marceloleite.tutorials.spring.kafka.consumer;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	private CountDownLatch countDownLatch = new CountDownLatch(3);

	@Value("${kafka.groupId}")
	private String groupId;

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}

	@KafkaListener(
			topics = "${kafka.topic}",
			groupId = "${kafka.groupId}",
			containerFactory = NomesBeans.CONCURRENT_KAFKA_LISTENER_CONTAINER_FACTORY)
	public void receberMensagem(String message) {
		System.out.println("Recebida a seguinte mensagem no grupo \"" + groupId + "\": " + message);
		countDownLatch.countDown();
	}
}