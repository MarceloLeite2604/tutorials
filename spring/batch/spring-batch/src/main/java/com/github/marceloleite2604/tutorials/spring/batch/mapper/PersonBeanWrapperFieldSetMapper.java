package com.github.marceloleite2604.tutorials.spring.batch.mapper;

import com.github.marceloleite2604.tutorials.spring.batch.model.Person;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;

public class PersonBeanWrapperFieldSetMapper extends BeanWrapperFieldSetMapper<Person> {

	public PersonBeanWrapperFieldSetMapper() {
		setTargetType(Person.class);
	}
}
