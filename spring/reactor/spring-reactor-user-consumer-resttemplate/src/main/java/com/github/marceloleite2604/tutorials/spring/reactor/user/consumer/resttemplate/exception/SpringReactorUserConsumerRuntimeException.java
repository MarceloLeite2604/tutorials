package com.github.marceloleite2604.tutorials.spring.reactor.user.consumer.resttemplate.exception;

public class SpringReactorUserConsumerRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public SpringReactorUserConsumerRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public SpringReactorUserConsumerRuntimeException(String message) {
    super(message);
  }
}
