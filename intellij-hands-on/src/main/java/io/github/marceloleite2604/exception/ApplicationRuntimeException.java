package io.github.marceloleite2604.exception;

import io.github.marceloleite2604.configuration.ApplicationApplicationContextAware;
import io.github.marceloleite2604.model.message.Message;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class ApplicationRuntimeException extends RuntimeException {

    private static final MessageSource MESSAGE_SOURCE = ApplicationApplicationContextAware.retrieveBean(MessageSource.class);

    private static final Locale LOCALE = ApplicationApplicationContextAware.retrieveBean(Locale.class);

    public ApplicationRuntimeException(Throwable cause, Message errorMessage, Object... parameters) {
        this(cause, errorMessage.getCode(), parameters);
    }

    public ApplicationRuntimeException(Message errorMessage, Object... parameters) {
        this(errorMessage.getCode(), parameters);
    }

    public ApplicationRuntimeException(String errorMessageCode, Object... parameters) {
        super(MESSAGE_SOURCE.getMessage(errorMessageCode, parameters, LOCALE));
    }

    public ApplicationRuntimeException(Throwable cause, String errorMessageCode, Object... parameters) {
        super(MESSAGE_SOURCE.getMessage(errorMessageCode, parameters, LOCALE), cause);
    }
}
