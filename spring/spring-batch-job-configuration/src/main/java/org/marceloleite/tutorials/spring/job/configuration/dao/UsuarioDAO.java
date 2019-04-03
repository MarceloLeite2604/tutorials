package org.marceloleite.tutorials.spring.job.configuration.dao;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.dao.repository.UsuarioRepository;
import org.marceloleite.tutorials.spring.job.configuration.model.po.UsuarioPO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDAO {

	@Inject
	private UsuarioRepository usuarioRepository;

	public Iterable<UsuarioPO> salvarTodos(Iterable<UsuarioPO> usuarioPOs) {
		return usuarioRepository.saveAll(usuarioPOs);
	}

	public void excluirTodos() {
		usuarioRepository.deleteAll();
	}
}
