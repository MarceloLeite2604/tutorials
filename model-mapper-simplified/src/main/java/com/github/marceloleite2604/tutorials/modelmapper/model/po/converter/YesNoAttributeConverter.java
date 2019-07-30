package com.github.marceloleite2604.tutorials.modelmapper.model.po.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class YesNoAttributeConverter implements AttributeConverter<Boolean, String> {
	
	protected static final String YES = "Y";
	
	protected static final String NO = "N";

	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		return attribute ? YES : NO;
	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		return YES.equalsIgnoreCase(dbData);
	}

}
