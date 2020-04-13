package io.github.marceloleite2604.model.message;

import java.io.File;

public enum ErrorMessage implements Message {

    UNKNOWN_ERROR(1),
    RECORD_NOT_FOUND(2),
    ID_IS_MANDATORY(3),
    ERROR_RETRIEVING_STACK_TRACE(4),
    PERSON_NOT_FOUND_WHILE_DELETING(5),
    ERROR_DELETE_PERSON(6),
    ERROR_GET_MESSAGE(7);

    public static final String FILE_PATH = "error/error".replace("/", File.separator);

    public static final String MESSAGE_PREFIX = "error.";

    private int value;

    private ErrorMessage(int value) {
        this.value = value;
    }

    @Override
    public String getCode() {
        return MESSAGE_PREFIX + value;
    }
}
