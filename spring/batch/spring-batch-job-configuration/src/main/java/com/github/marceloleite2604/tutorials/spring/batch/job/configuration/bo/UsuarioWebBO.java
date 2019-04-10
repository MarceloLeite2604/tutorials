package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.bo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.dao.UsuarioWebDAO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.json.UserJsonVO;

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
