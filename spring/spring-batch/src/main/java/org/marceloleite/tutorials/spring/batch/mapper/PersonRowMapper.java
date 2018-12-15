package org.marceloleite.tutorials.spring.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.marceloleite.tutorials.spring.batch.model.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonRowMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		return new Person(resultSet.getString(1), resultSet.getString(2));
	}

}
