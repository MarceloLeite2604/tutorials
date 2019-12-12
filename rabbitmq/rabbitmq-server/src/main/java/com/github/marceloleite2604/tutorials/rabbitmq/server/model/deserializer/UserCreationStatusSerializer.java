package com.github.marceloleite2604.tutorials.rabbitmq.server.model.deserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.marceloleite2604.tutorials.rabbitmq.server.model.UserCreationStatus;
import java.io.IOException;

public class UserCreationStatusSerializer extends JsonSerializer<UserCreationStatus> {

	@Override
	public void serialize(UserCreationStatus userCreationStatus, JsonGenerator jsonGenerator,
			SerializerProvider serializedProvider) throws IOException {
		jsonGenerator.writeNumber(userCreationStatus.getCode());
	}

}
