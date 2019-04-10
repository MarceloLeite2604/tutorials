package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.exception.SpringBatchJobConfigurationRuntimeException;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.json.ResponseJsonVO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.json.UserJsonVO;

@Component
public class UsuarioWebDAO {

	@Inject
	private RestTemplate restTemplate;

	public UserJsonVO obter() {
		return obter(1).get(0);
	}

	public List<UserJsonVO> obter(int quantidade) {
		ResponseEntity<ResponseJsonVO> responseEntity = restTemplate
				.exchange(criarEndereco(quantidade), HttpMethod.GET, null, ResponseJsonVO.class);
		return responseEntity.getBody()
				.getUserJsonVOs();
	}

	private URI criarEndereco(int totalResultados) {

		try {
			return new URIBuilder().setScheme("https")
					.setHost("randomuser.me")
					.setPath("api")
					.addParameter("results", Integer.toString(totalResultados))
					.build();
		} catch (URISyntaxException exception) {
			throw new SpringBatchJobConfigurationRuntimeException(
					"Erro ao construir a URI do serviço de aquisição dos usuários.", exception);
		}
	}
}
