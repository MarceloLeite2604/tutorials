package com.github.marceloleite2604.tutorials.spring.kafka;

import java.util.Random;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

	@Inject
	private KafkaTemplate<String, String> kafkaTemplate;

	private Random random = new Random();

	@Value(value = "${kafka.topic}")
	private String topic;

	@GetMapping("/send-message")
	public String sendMessage() {
		long valor = random.nextLong();
		sendMessage(valor);
		return "Valor enviado: " + valor;
	}

	private void sendMessage(Long value) {

		String message = value.toString();

		ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic,
				message);

		listenableFuture.addCallback(new SendMessageListenableFutureCallback(message));
	}
}
