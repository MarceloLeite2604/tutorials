package com.github.marceloleite2604.tutorials.modelmapper.util.message;

public enum GameMessage implements Message {

	CREATED("edit.created"),
	MODIFIED("edit.modified"),
	DELETED("deleted");

	private static final String PREFIX = "game.";

	private String code;

	private GameMessage(String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return PREFIX + code;
	}
}
