package com.github.marceloleite2604.tutorials.modelmapper.exception;

import java.util.Optional;

public class InvalidBeanTypeRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_TEMPLATE = "Requested bean should be of type \"%s\", but it is \"%s\".";

	public InvalidBeanTypeRuntimeException(Object bean, Class<?> clazz) {
		super(String.format(MESSAGE_TEMPLATE, retrieveClassName(clazz),
				retrieveClassName(bean)));
	}

	private static String retrieveClassName(Object object) {
		return Optional.ofNullable(object)
				.map(Object::getClass)
				.map(Class::getName)
				.orElse(null);
	}
}