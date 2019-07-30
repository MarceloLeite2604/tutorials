package com.github.marceloleite2604.tutorials.modelmapper.exception;

import com.github.marceloleite2604.tutorials.modelmapper.util.ModelMapperApplicationContext;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.ErrorMessage;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.MessageLoader;

public abstract class ModelMapperException extends Exception {

	private static final long serialVersionUID = 1L;

	protected static final MessageLoader MESSAGE_LOADER = ModelMapperApplicationContext.getMessageLoader();

	public ModelMapperException(Throwable cause, ErrorMessage errorMessage, Object... messageParameters) {
		super(MESSAGE_LOADER.getMessage(errorMessage, messageParameters), cause);
	}

	public ModelMapperException(ErrorMessage errorMessage, Object... messageParameters) {
		super(MESSAGE_LOADER.getMessage(errorMessage, messageParameters));
	}
}
