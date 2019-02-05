package org.marceloleite.tutorials.spring.job.configuration.bo;

import java.util.List;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.dao.UsuarioWebDAO;
import org.marceloleite.tutorials.spring.job.configuration.model.json.UserJsonVO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioWebBO {

	@Inject
	private UsuarioWebDAO userWebDAO;

	public UserJsonVO obter() {
		return userWebDAO.obter();
	}
	
	public List<UserJsonVO> obter(int quantidade) {
		return userWebDAO.obter(quantidade);
	}
}
