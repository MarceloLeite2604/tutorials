package org.marceloleite.tutorials.spring.job.configuration.job.step.aquisicaousuarios.reader;

import java.util.Iterator;

import org.marceloleite.tutorials.spring.job.configuration.bo.UsuarioWebBO;
import org.marceloleite.tutorials.spring.job.configuration.diversos.ItemBuffer;
import org.marceloleite.tutorials.spring.job.configuration.model.json.UserJsonVO;

public class UsuarioWebBuffer extends ItemBuffer<UserJsonVO> {

	private int tamanhoDoChunk;

	private UsuarioWebBO usuarioWebBO;

	public UsuarioWebBuffer(UsuarioWebBO usuarioWebBO, int tamanhoDoChunk) {
		this.usuarioWebBO = usuarioWebBO;
		this.tamanhoDoChunk = tamanhoDoChunk;
	}

	@Override
	protected Iterator<UserJsonVO> get() {
		return usuarioWebBO.obter(tamanhoDoChunk)
				.iterator();
	}

}
