package org.marceloleite.tutorials.spring.batch.mapper;

import org.marceloleite.tutorials.spring.batch.model.Person;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;

public class PersonBeanWrapperFieldSetMapper extends BeanWrapperFieldSetMapper<Person> {

	public PersonBeanWrapperFieldSetMapper() {
		setTargetType(Person.class);
	}
}
