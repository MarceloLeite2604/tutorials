package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.bo;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.dao.UsuarioDAO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.po.UsuarioPO;

@Component
public class UsuarioBO {

	@Inject
	private UsuarioDAO usuarioDAO;
	
	public Iterable<UsuarioPO> salvarTodos(Iterable<UsuarioPO> usuarioPOs) {
		return usuarioDAO.salvarTodos(usuarioPOs);
	}

	public void excluirTodos() {
		usuarioDAO.excluirTodos();
	}

}
