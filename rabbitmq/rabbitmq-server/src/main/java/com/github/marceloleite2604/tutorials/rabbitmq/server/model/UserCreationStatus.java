package com.github.marceloleite2604.tutorials.rabbitmq.server.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.marceloleite2604.tutorials.rabbitmq.server.model.deserializer.UserCreationStatusSerializer;

@JsonSerialize(using = UserCreationStatusSerializer.class)
public enum UserCreationStatus {

	SUCCESS(0),
	WARNING(1),
	FAILURE(2);

	private int code;

	private UserCreationStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
