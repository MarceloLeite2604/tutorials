package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.dao;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.dao.repository.UsuarioRepository;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.po.UsuarioPO;

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
