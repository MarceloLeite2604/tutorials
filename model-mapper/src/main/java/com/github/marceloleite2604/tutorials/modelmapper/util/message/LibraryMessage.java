package com.github.marceloleite2604.tutorials.modelmapper.util.message;

public enum LibraryMessage implements Message {

	CREATED("edit.created"),
	MODIFIED("edit.modified"),
	DELETED("deleted");

	private static final String PREFIX = "library.";

	private String code;

	private LibraryMessage(String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return PREFIX + code;
	}
}
