package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.aquisicaousuarios.reader;

import java.util.Iterator;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.bo.UsuarioWebBO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.diversos.ItemBuffer;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.json.UserJsonVO;

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
