package io.github.marceloleite2604.configuration;

import com.figtreelake.sled.Sled;
import com.figtreelake.util.time.local.LocalDateTimeUtil;
import io.github.marceloleite2604.properties.ApplicationProperties;
import io.github.marceloleite2604.properties.EncryptionProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class ApplicationConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @Bean(BeanNames.SLED)
    public Sled createSled(EncryptionProperties encryptionProperties) {
        return Sled.builder()
                .cryptographicAlgorithm(encryptionProperties.getCryptographicAlgorithm())
                .feedbackMode(encryptionProperties.getFeedbackMode())
                .paddingScheme(encryptionProperties.getPaddingScheme())
                .build();
    }

    @Bean(BeanNames.LOCALE)
    public Locale createLocale(ApplicationProperties applicationProperties) {
        Locale locale;
        String localeProperty = applicationProperties.getLocale();
        if (!StringUtils.isBlank(localeProperty)) {
            LOGGER.info("Setting program language to \"{}\".", localeProperty);
            locale = Locale.forLanguageTag(localeProperty);
        } else {
            locale = Locale.getDefault();
        }
        return locale;
    }

    @Bean(BeanNames.LOCALE_RESOLVER)
    public LocaleResolver createLocaleResolver(Locale locale) {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(locale);
        return sessionLocaleResolver;
    }

    @Bean(BeanNames.LOCAL_DATE_TIME_UTIL)
    public LocalDateTimeUtil createLocalDateTimeUtil() {
        return new LocalDateTimeUtil();
    }

    @Bean(BeanNames.LOCAL_VALIDATOR_FACTORY_BEAN)
    public LocalValidatorFactoryBean createLocalValidatorFactoryBean(MessageSource messageSource) {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        return localValidatorFactoryBean;
    }
}
