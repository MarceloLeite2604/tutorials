package com.github.marceloleite2604.tutorials.rabbitmq.server;

import com.github.marceloleite2604.tutorials.rabbitmq.server.exception.RabbitMqConsumerRuntimeException;
import com.github.marceloleite2604.tutorials.rabbitmq.server.model.UserCreationResponse;
import com.github.marceloleite2604.tutorials.rabbitmq.server.model.mapper.UserCreationResponseToByArrayMapper;
import com.github.marceloleite2604.tutorials.rabbitmq.server.properties.RabbitMqProperties;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserCreationResponseProducer {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserCreationResponseProducer.class);

	@Inject
	private UserCreationResponseToByArrayMapper userCreationResponseToByArrayMapper;

	@Inject
	private Channel channel;

	@Inject
	private RabbitMqProperties rabbitMqProperties;

	public void sendUserCreationResponse(UserCreationResponse userCreationResponse) {

		LOGGER.info("Sending response {}.", userCreationResponse);

		try {
			publishResponseOnChannel(userCreationResponse);
		} catch (IOException exception) {
			String message = String.format("Error while sending \"%s\" message on \"%s\" queue.",
					userCreationResponse, rabbitMqProperties.getResponseTopic());
			throw new RabbitMqConsumerRuntimeException(message, exception);
		}

	}

	private void publishResponseOnChannel(UserCreationResponse userCreationResponse)
			throws IOException {
		byte[] message = userCreationResponseToByArrayMapper.map(userCreationResponse);

		channel.basicPublish(rabbitMqProperties.getResponseTopic(),
				rabbitMqProperties.getResponseRoutingKey(), false, false, null, message);
	}

}
