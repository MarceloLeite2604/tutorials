package com.github.marceloleite2604.tutorials.rabbitmq.server.model.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marceloleite2604.tutorials.rabbitmq.server.exception.RabbitMqConsumerRuntimeException;
import com.github.marceloleite2604.tutorials.rabbitmq.server.model.User;
import java.io.IOException;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class ByteArrayToUserMapper {

	@Inject
	private ObjectMapper objectMapper;

	public User map(byte[] bytes) {
		try {
			return objectMapper.readValue(bytes, User.class);
		} catch (IOException exception) {
			String message = String.format("Error while mapping byte array to \"%s\" object.",
					User.class.getSimpleName());
			throw new RabbitMqConsumerRuntimeException(message, exception);
		}
	}
}
