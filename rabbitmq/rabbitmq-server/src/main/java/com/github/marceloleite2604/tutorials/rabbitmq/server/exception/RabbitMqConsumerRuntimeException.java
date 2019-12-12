package com.github.marceloleite2604.tutorials.rabbitmq.server.exception;

public class RabbitMqConsumerRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RabbitMqConsumerRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public RabbitMqConsumerRuntimeException(String message) {
		super(message);
	}
}
