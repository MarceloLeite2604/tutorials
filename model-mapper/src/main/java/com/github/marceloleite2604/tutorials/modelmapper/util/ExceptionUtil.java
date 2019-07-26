package com.github.marceloleite2604.tutorials.modelmapper.util;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.modelmapper.exception.ModelMapperRuntimeException;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.ErrorMessage;

@Component
public class ExceptionUtil {

	public String retrieveStackTrace(Exception exception) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		try (PrintStream printStream = new PrintStream(byteArrayOutputStream, true,
				StandardCharsets.UTF_8.toString())) {
			exception.printStackTrace(printStream);
		} catch (UnsupportedEncodingException unsupportedEncodingException) {
			throw new ModelMapperRuntimeException(unsupportedEncodingException,
					ErrorMessage.ERROR_RETRIEVING_STACK_TRACE);
		}

		return new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
	}
}
