package com.github.marceloleite2604.tutorials.modelmapper.util.message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {

	@Inject
	private MessageLoader messageLoader;

	private static final String MESSAGE_CODE_CAPTURE_GROUP_NAME = "messageCode";

	private static final Pattern MESSAGE_CODE_PATTERN = Pattern
			.compile("#\\{(?<" + MESSAGE_CODE_CAPTURE_GROUP_NAME + ">[\\w\\.-]+)\\}");

	public String formatMessage(String message, Object... parameters) {

		String messageCode = retrieveMessageCode(message);

		if (!StringUtils.isBlank(messageCode)) {
			return retrieveMessageFromLoader(messageCode, parameters);
		}

		return String.format(message, parameters);
	}

	private String retrieveMessageFromLoader(String messageCode, Object... parameters) {
		return messageLoader.getMessage(messageCode, parameters);

	}

	private String retrieveMessageCode(String message) {
		Matcher matcher = MESSAGE_CODE_PATTERN.matcher(message);
		if (matcher.matches()) {
			return matcher.group(MESSAGE_CODE_CAPTURE_GROUP_NAME);
		}
		return null;
	}
}
