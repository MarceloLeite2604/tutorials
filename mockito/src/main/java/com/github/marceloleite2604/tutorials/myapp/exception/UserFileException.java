package com.github.marceloleite2604.tutorials.myapp.exception;

public class UserFileException extends MyAppException{

    public UserFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserFileException(String message) {
        super(message);
    }
}
