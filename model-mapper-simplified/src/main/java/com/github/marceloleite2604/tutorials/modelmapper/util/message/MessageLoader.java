package com.github.marceloleite2604.tutorials.modelmapper.util.message;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.context.MessageSource;

public class MessageLoader {

	static final String SINGULAR_SUFFIX = ".singular";

	static final String PLURAL_SUFFIX = ".plural";

	private MessageSource messageSource;

	private Locale locale;

	public MessageLoader(MessageSource messageSource, Locale locale) {
		this.messageSource = messageSource;
		this.locale = locale;
	}

	public String getMessage(Message message, Object... parameters) {
		return getMessage(message.getCode(), parameters);
	}

	public String getMessage(String code, Object... parameters) {
		try {
			return messageSource.getMessage(code, parameters, locale);
		} catch (Exception exception) {
			throw new MessageLoaderException(
					"Não foi possivel localizar uma mensagem com o código \"" + code + "\".",
					exception);
		}
	}

	public String getSingularOrPluralMessage(boolean singular, String codePrefix) {
		return getSingularOrPluralMessage(singular, codePrefix, null, null);
	}

	public String getSingularOrPluralMessage(boolean singular, String codePrefix,
			List<Object> singularParameters, List<Object> pluralParameters) {

		String code = codePrefix.concat(singular ? SINGULAR_SUFFIX : PLURAL_SUFFIX);

		List<Object> parametersList = singular ? singularParameters : pluralParameters;

		Object[] parameters = null;

		if (!Objects.isNull(parametersList)) {
			parameters = parametersList.toArray();
		}

		return getMessage(code, parameters);
	}

	static class MessageLoaderException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public MessageLoaderException(String mensagem, Throwable motivo) {
			super(mensagem, motivo);
		}
	}

}