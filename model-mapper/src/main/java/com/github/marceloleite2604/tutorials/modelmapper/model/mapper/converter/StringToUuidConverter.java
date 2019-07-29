package com.github.marceloleite2604.tutorials.modelmapper.model.mapper.converter;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class StringToUuidConverter implements Converter<String, UUID> {

	@Override
	public UUID convert(MappingContext<String, UUID> context) {
		String string = context.getSource();

		if (StringUtils.isBlank(string)) {
			return null;
		}

		return UUID.fromString(string);
	}
}
