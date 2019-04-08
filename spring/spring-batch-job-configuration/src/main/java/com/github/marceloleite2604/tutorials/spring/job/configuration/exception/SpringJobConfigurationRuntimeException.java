package com.github.marceloleite2604.tutorials.spring.job.configuration.exception;

public class SpringJobConfigurationRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SpringJobConfigurationRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpringJobConfigurationRuntimeException(String message) {
		super(message);
	}
}
