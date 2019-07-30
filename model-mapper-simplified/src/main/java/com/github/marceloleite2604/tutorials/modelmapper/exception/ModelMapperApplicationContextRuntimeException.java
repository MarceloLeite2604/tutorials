package com.github.marceloleite2604.tutorials.modelmapper.exception;


public class ModelMapperApplicationContextRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ModelMapperApplicationContextRuntimeException(String message) {
		super(message);
	}
	
	public ModelMapperApplicationContextRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

}
