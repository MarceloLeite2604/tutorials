package io.github.marceloleite2604.util;

import io.github.marceloleite2604.configuration.BeanNames;
import io.github.marceloleite2604.model.message.Message;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Locale;

@Component(BeanNames.MESSAGE_RETRIEVER)
public class MessageRetriever {

    private final MessageSource messageSource;

    private final Locale locale;

    @Inject
    public MessageRetriever(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    public String getMessage(Message message, Object... parameters) {
        return messageSource.getMessage(message.getCode(), parameters, locale);
    }

    public String getMessage(String code, Object... parameters) {
        return messageSource.getMessage(code, parameters, locale);
    }
}
