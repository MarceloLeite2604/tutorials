package com.github.marceloleite2604.tutorials.myapp.exception;

public class MyAppRuntimeException extends RuntimeException {

    public MyAppRuntimeException(String message) {
        super(message);
    }

    public MyAppRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
