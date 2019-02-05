package org.marceloleite.tutorials.spring.job.configuration;

import java.util.Iterator;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.bo.UsuarioWebBO;
import org.marceloleite.tutorials.spring.job.configuration.model.json.UserJsonVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsuarioWebBuffer extends ItemBuffer<UserJsonVO> {

	@Value("${programa.tamanhoDoChunk}")
	private int tamanhoDoChunk;

	@Inject
	private UsuarioWebBO usuarioWebBO;

	@Override
	protected Iterator<UserJsonVO> get() {
		return usuarioWebBO.obter(tamanhoDoChunk).iterator();
	}

}
