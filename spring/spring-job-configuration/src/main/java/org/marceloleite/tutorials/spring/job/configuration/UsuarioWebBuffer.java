package org.marceloleite.tutorials.spring.job.configuration;

import java.util.Iterator;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.bo.UsuarioWebBO;
import org.marceloleite.tutorials.spring.job.configuration.model.json.UserJsonVO;

public class UsuarioWebBuffer extends ItemBuffer<UserJsonVO> {

	private int tamanhoDoChunk;

	public UsuarioWebBuffer(int tamanhoDoChunk) {
		this.tamanhoDoChunk = tamanhoDoChunk;
	}

	@Inject
	private UsuarioWebBO usuarioWebBO;

	@Override
	protected Iterator<UserJsonVO> get() {
		return usuarioWebBO.obter(tamanhoDoChunk)
				.iterator();
	}

}
