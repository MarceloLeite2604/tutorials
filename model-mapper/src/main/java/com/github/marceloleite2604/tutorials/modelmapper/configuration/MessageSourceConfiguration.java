package com.github.marceloleite2604.tutorials.modelmapper.configuration;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.github.marceloleite2604.tutorials.modelmapper.util.message.ErrorMessage;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.MessageLoader;

@Configuration
public class MessageSourceConfiguration {

	private static final String PAGES_MESSAGE_PROPERTIES_FILE_PATH = "classpath:i18n/messages/pages/messages";

	public static final Locale LOCALE_BRAZILIAN_PORTUGUESE = Locale.forLanguageTag("pt-BR");

	@Bean(BeanNames.LOCALE_RESOLVER)
	public LocaleResolver createLocaleResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(LOCALE_BRAZILIAN_PORTUGUESE);
		return sessionLocaleResolver;
	}

	@Bean(BeanNames.MESSAGE_SOURCE)
	public MessageSource createMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(PAGES_MESSAGE_PROPERTIES_FILE_PATH,
				ErrorMessage.MESSAGE_PROPERTIES_FILE_PATH);
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		return messageSource;
	}

	@Bean(BeanNames.MESSAGE_LOADER)
	public MessageLoader createMessageLoader(MessageSource messageSource) {
		return new MessageLoader(messageSource, LOCALE_BRAZILIAN_PORTUGUESE);
	}

	@Bean
	public LocalValidatorFactoryBean getValidator(MessageSource messageSource) {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);
		return bean;
	}

}
