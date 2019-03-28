package com.github.marceloleite2604.tutorials.spring.cloud.stream.client.inversor;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;

public class TimeoutMessageHandler implements MessageHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimeoutMessageHandler.class);

	private CountDownLatch countDownLatch;

	private Optional<String> texto;

	public TimeoutMessageHandler() {
		countDownLatch = new CountDownLatch(1);
		texto = Optional.empty();
	}

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}

	public Optional<String> getTexto() {
		return texto;
	}

	@Override
	public void handleMessage(Message<?> message) {
		this.texto = Optional.ofNullable(new String((byte[]) message.getPayload()));
		LOGGER.debug("Recebido o seguinte texto: \"{}\".", texto);
		countDownLatch.countDown();
	}

}
