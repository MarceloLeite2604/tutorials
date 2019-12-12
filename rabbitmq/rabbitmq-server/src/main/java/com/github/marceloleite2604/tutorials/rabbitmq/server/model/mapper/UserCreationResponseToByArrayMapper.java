package com.github.marceloleite2604.tutorials.rabbitmq.server.model.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marceloleite2604.tutorials.rabbitmq.server.exception.RabbitMqConsumerRuntimeException;
import com.github.marceloleite2604.tutorials.rabbitmq.server.model.UserCreationResponse;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

@Component
public class UserCreationResponseToByArrayMapper {

	@Inject
	private ObjectMapper objectMapper;

	public byte[] map(UserCreationResponse userCreationResponse) {
		try {
			return objectMapper.writeValueAsBytes(userCreationResponse);
		} catch (JsonProcessingException exception) {
			String message = String.format("Error while mapping \"%s\" object to byte array.",
					UserCreationResponse.class.getSimpleName());
			throw new RabbitMqConsumerRuntimeException(message, exception);
		}
	}
}
