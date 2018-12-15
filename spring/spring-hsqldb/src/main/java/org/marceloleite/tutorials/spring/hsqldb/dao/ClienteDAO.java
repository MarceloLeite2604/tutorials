package org.marceloleite.tutorials.spring.hsqldb.dao;

import java.util.List;
import java.util.UUID;

import org.marceloleite.tutorials.spring.hsqldb.model.Cliente;

public interface ClienteDAO {

	Cliente obterPorId(UUID id);

	long contarClientes();
	
	int inserir(Cliente cliente);
	
	int inserir(List<Cliente> clientes);

}
