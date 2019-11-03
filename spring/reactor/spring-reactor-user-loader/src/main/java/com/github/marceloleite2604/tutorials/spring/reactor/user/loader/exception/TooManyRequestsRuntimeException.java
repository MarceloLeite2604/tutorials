package com.github.marceloleite2604.tutorials.spring.reactor.user.loader.exception;

public class TooManyRequestsRuntimeException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public TooManyRequestsRuntimeException(String message) {
    super(message);
  }

}
