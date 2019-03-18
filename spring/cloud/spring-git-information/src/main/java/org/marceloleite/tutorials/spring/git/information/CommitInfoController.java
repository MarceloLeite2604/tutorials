package org.marceloleite.tutorials.spring.git.information;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.boot.info.GitProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("git-information")
public class CommitInfoController {

	/*
	 * Não é necessário uma vez que o próprio Spring cria um objeto "GitProperties"
	 * com as informações do projeto.
	 */
	// @Inject
	// @Named("gitProperties")
	// private Properties gitProperties;

	@Inject
	@Named("gitProperties")
	private GitProperties gitProperties;

	@RequestMapping("/properties")
	public Map<String, String> getCommitId() {
		Map<String, String> resultado = new HashMap<>();
		gitProperties.forEach(entry -> resultado.put(entry.getKey(), entry.getValue()));
		return resultado;
	}
}