package com.github.marceloleite2604.tutorials.modelmapper.util;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Component
public class ObjectUtil {

	private static final String CLASS_PROPERTY_NAME = "class";

	public boolean isBlankOrNull(Object object) {
		return (isBlankOrNullString(object) || object == null);
	}

	private boolean isBlankOrNullString(Object object) {
		return (object instanceof String && StringUtils.isBlank((String) object));
	}

	public boolean allFieldsAreEmpty(Object object) {
		BeanWrapper beanWrapper = createBeanWrapper(object);
		List<String> propertyNames = retrievePropertyNames(beanWrapper);
		return propertyNames.stream()
				.map(beanWrapper::getPropertyValue)
				.allMatch(Objects::isNull);
	}

	public List<String> retrievePropertyNames(BeanWrapper beanWrapper) {
		return Arrays.asList(beanWrapper.getPropertyDescriptors())
				.stream()
				.map(PropertyDescriptor::getName)
				.filter(name -> !CLASS_PROPERTY_NAME.equals(name))
				.collect(Collectors.toList());
	}

	public BeanWrapper createBeanWrapper(Object object) {
		return new BeanWrapperImpl(object);
	}
}
