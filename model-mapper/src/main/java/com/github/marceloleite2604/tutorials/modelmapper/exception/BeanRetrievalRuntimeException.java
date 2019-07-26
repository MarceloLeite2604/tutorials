package com.github.marceloleite2604.tutorials.modelmapper.exception;

import java.util.Optional;

public class BeanRetrievalRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_TEMPLATE = "Error while retrieving bean \"%s\".";

	public BeanRetrievalRuntimeException(String beanName, Throwable cause) {
		super(String.format(MESSAGE_TEMPLATE, beanName), cause);
	}

	public BeanRetrievalRuntimeException(Class<?> clazz, Throwable cause) {
		super("Could not find bean \"" + retrieveClassName(clazz) + "\".", cause);
	}

	private static String retrieveClassName(Class<?> clazz) {
		return Optional.ofNullable(clazz)
				.map(Class::getName)
				.orElse(null);
	}
}