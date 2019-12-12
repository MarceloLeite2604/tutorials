package com.github.marceloleite2604.tutorials.rabbitmq.server;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.github.marceloleite2604.tutorials.rabbitmq.server.exception.RabbitMqConsumerRuntimeException;
import com.github.marceloleite2604.tutorials.rabbitmq.server.model.User;
import com.github.marceloleite2604.tutorials.rabbitmq.server.model.UserCreationResponse;
import com.github.marceloleite2604.tutorials.rabbitmq.server.model.UserCreationStatus;
import com.github.marceloleite2604.tutorials.rabbitmq.server.model.mapper.ByteArrayToUserMapper;
import com.github.marceloleite2604.tutorials.rabbitmq.server.properties.RabbitMqProperties;
import com.github.marceloleite2604.tutorials.rabbitmq.server.properties.RabbitMqServerProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserCreationRequestConsumer extends DefaultConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserCreationRequestConsumer.class);

	private static final String SUCCESS_SUMMARY = "User created successfully.";

	private static final String FAILURE_SUMMARY = "Could not create user.";

	private static final String FAILURE_MESSAGE = "Out of cheese error.";

	@Inject
	private ByteArrayToUserMapper byteArrayToUserMapper;

	@Inject
	private UserCreationResponseProducer userCreationResponseProducer;

	@Inject
	private RabbitMqProperties rabbitMqProperties;

	@Inject
	private RabbitMqServerProperties rabbitMqServerProperties;

	private Random random;

	public UserCreationRequestConsumer(Channel channel) {
		super(channel);
		this.random = new Random();
	}

	@PostConstruct
	public void postConstruct() throws IOException {
		getChannel().basicConsume(rabbitMqProperties.getRequestQueue(), this);
		LOGGER.info("RabbitMQ \"{}\" queue consumer is up.",
				rabbitMqProperties.getRequestQueue());
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
			byte[] body) throws IOException {

		User user = byteArrayToUserMapper.map(body);
		LOGGER.info("Received user creation request {}.", user);
		
		UserCreationResponse userCreationResponse = createResponse(user);

		userCreationResponseProducer.sendUserCreationResponse(userCreationResponse);

		acknowledgeMessage(envelope);
	}

	private UserCreationResponse createResponse(User user) {
		UserCreationResponse userCreationResponse;
		if (shouldSendSuccessResponse()) {
			user.setId(UUID.randomUUID());
			userCreationResponse = createSuccesReponse(user);
		} else {
			userCreationResponse = createFailureReponse(user);
		}
		return userCreationResponse;
	}

	private void acknowledgeMessage(Envelope envelope) {
		long deliveryTag = envelope.getDeliveryTag();
		try {
			getChannel().basicAck(deliveryTag, false);
		} catch (IOException exception) {
			String message = String.format("Error while acknowledging message \"%d\".",
					deliveryTag);
			throw new RabbitMqConsumerRuntimeException(message, exception);
		}
	}

	private boolean shouldSendSuccessResponse() {
		return (random.nextDouble() >= rabbitMqServerProperties.getFailureRatio());
	}

	private UserCreationResponse createSuccesReponse(User user) {
		return UserCreationResponse.builder()
				.user(user)
				.summary(SUCCESS_SUMMARY)
				.status(UserCreationStatus.SUCCESS)
				.build();
	}

	private UserCreationResponse createFailureReponse(User user) {
		return UserCreationResponse.builder()
				.user(user)
				.summary(FAILURE_SUMMARY)
				.messages(Arrays.asList(FAILURE_MESSAGE))
				.status(UserCreationStatus.FAILURE)
				.build();
	}

}
