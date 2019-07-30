package com.github.marceloleite2604.tutorials.modelmapper.configuration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marceloleite2604.encryptor.EncryptorDecryptor;
import com.github.marceloleite2604.tutorials.modelmapper.exception.ModelMapperRuntimeException;
import com.github.marceloleite2604.tutorials.modelmapper.properties.EncryptionProperties;
import com.github.marceloleite2604.tutorials.modelmapper.util.EncryptionUtil;
import com.github.marceloleite2604.tutorials.modelmapper.util.message.ErrorMessage;

@Configuration
public class EncryptionConfiguration {

	@Bean(BeanNames.ENCRYPTION_UTIL)
	public EncryptionUtil createEncryptionUtil(EncryptionProperties encryptionProperties) {

		if (StringUtils.isBlank(encryptionProperties.getKey())
				&& StringUtils.isBlank(encryptionProperties.getKeyEnvironmentVariableName())) {
			throw new ModelMapperRuntimeException(ErrorMessage.ENCRYPTION_KEY_NOT_INFORMED);
		}

		EncryptionUtil.Builder builder = EncryptionUtil.builder()
				.encryptorDecryptor(createEncryptorDecryptor(encryptionProperties));

		if (StringUtils.isBlank(encryptionProperties.getKeyEnvironmentVariableName())) {
			builder.key(encryptionProperties.getKeyEnvironmentVariableName());
		}

		builder.key(encryptionProperties.getKey());

		return builder.build();

	}

	private EncryptorDecryptor createEncryptorDecryptor(EncryptionProperties encryptionProperties) {
		EncryptorDecryptor.Builder builder = EncryptorDecryptor.builder()
				.cryptographicAlgorythm(encryptionProperties.getCryptographicAlgorythm())
				.feedbackMode(encryptionProperties.getFeedbackMode())
				.paddingScheme(encryptionProperties.getPaddingScheme());

		if (StringUtils.isNotBlank(encryptionProperties.getKeyEnvironmentVariableName())) {
			builder.keyEnvironmentVariableName(encryptionProperties.getKeyEnvironmentVariableName());
		}

		return builder.build();
	}

}
