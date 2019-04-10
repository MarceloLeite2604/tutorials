package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.exception;

public class SpringBatchJobConfigurationRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SpringBatchJobConfigurationRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpringBatchJobConfigurationRuntimeException(String message) {
		super(message);
	}
}
