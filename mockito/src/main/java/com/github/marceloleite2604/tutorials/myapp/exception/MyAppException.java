package com.github.marceloleite2604.tutorials.myapp.exception;

public class MyAppException extends Exception {

    public MyAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyAppException(String message) {
        super(message);
    }
}
