package com.github.marceloleite2604.tutorials.modelmapper.util.message;

public enum PageUserMessage implements Message {

	CREATED("edit.created"),
	MODIFIED("edit.modified"),
	PASSWORD_NOT_BLANK("edit.password.validation.not-blank");

	private static final String PREFIX = "user.";

	private String code;

	private PageUserMessage(String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return PREFIX + code;
	}
}
