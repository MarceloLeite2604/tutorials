package io.github.marceloleite2604.util;

import io.github.marceloleite2604.exception.ApplicationRuntimeException;
import io.github.marceloleite2604.model.message.ErrorMessage;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Component
public class ExceptionUtil {

    public String retrieveStackTrace(Exception exception) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (PrintStream printStream = new PrintStream(byteArrayOutputStream, true,
                StandardCharsets.UTF_8.toString())) {
            exception.printStackTrace(printStream);

        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new ApplicationRuntimeException(unsupportedEncodingException,
                    ErrorMessage.ERROR_RETRIEVING_STACK_TRACE);
        }

        return new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
    }
}
