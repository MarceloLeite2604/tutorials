package com.github.marceloleite2604.tutorials.modelmapper.util.message;

import java.io.File;

public enum ErrorMessage implements Message {

	UNKNOWN_ERROR(1),
	ENCRYPTION_KEY_NOT_INFORMED(2),
	PERSISTENT_OBJECT_NOT_FOUND(3),
	ERROR_RETRIEVING_STACK_TRACE(4);

	private static final String MESSAGE_CODE_PREFIX = "error";

	public static final String MESSAGE_PROPERTIES_FILE_PATH = "classpath:i18n/messages/error/messages"
			.replace("/", File.separator);

	private Integer code;

	private ErrorMessage(Integer code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return MESSAGE_CODE_PREFIX + "." + code.toString();
	}
}
