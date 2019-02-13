package org.marceloleite.tutorials.spring.job.configuration.bo;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.dao.UsuarioDAO;
import org.marceloleite.tutorials.spring.job.configuration.model.po.UsuarioPO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioBO {

	@Inject
	private UsuarioDAO usuarioDAO;
	
	public Iterable<UsuarioPO> salvarTodos(Iterable<UsuarioPO> usuarioPOs) {
		return usuarioDAO.salvarTodos(usuarioPOs);
	}

}
