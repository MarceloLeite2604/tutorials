package org.marceloleite.tutorials.spring.hsqldb.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.marceloleite.tutorials.spring.hsqldb.model.Cliente;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteRowMapper implements RowMapper<Cliente> {

	@Override
	public Cliente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		return Cliente.builder()
				.id(UUID.fromString(resultSet.getString("id")))
				.valor(resultSet.getLong("valor"))
				.build();
	}

}
