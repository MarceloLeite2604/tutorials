package io.github.marceloleite2604.exception;

public class ApplicationContextRuntimeException extends RuntimeException {

    public ApplicationContextRuntimeException(String message) {
        super(message);
    }

    public ApplicationContextRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
