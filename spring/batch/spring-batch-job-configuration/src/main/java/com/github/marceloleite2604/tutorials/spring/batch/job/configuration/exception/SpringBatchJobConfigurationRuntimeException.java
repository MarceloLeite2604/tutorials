package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.exception;

public class SpringBatchJobConfigurationRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SpringBatchJobConfigurationRuntimeException(String mensagem, Throwable motivo) {
		super(mensagem, motivo);
	}

	public SpringBatchJobConfigurationRuntimeException(String mensagem) {
		super(mensagem);
	}
}
