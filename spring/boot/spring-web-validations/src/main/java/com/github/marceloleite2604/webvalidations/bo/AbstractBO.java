package com.github.marceloleite2604.webvalidations.bo;

import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.SmartValidator;

public abstract class AbstractBO<T> {

	@Inject
	private SmartValidator smartValidator;

	private Class<T> entityClass;
	
	private Class<?> databaseValidationClass;

	protected AbstractBO(Class<T> entityClass, Class<?> databaseValidationClass) {
		this.entityClass = entityClass;
		this.databaseValidationClass = databaseValidationClass;
	}
	
	protected void validate(T entity) {
		Errors errors = new BeanPropertyBindingResult(entity, entityClass.getName());
		smartValidator.validate(entity, errors, databaseValidationClass);
		if (errors.hasErrors()) {
			throw new RuntimeException(String.join(" ", errors.getAllErrors()
					.stream()
					.map(ObjectError::getDefaultMessage)
					.collect(Collectors.toList())));
		}
	}
}
