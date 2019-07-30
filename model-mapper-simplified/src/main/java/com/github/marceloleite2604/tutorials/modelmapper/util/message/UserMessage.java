package com.github.marceloleite2604.tutorials.modelmapper.util.message;

public enum UserMessage implements Message {

	CREATED("edit.created"),
	MODIFIED("edit.modified"),
	PASSWORD_NOT_BLANK("edit.password.validation.not-blank"),
	DELETED("deleted");

	private static final String PREFIX = "user.";

	private String code;

	private UserMessage(String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return PREFIX + code;
	}
}
