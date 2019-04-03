package org.marceloleite.tutorials.spring.job.configuration.propriedade.job.aquisicaousuarios;

import org.marceloleite.tutorials.spring.job.configuration.propriedade.CaminhosPropriedades;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(CaminhosPropriedades.AQUISICAO_USUARIOS)
public class AquisicaoUsuariosProperties {

	private long totalDeRegistros;

	private int tamanhoDoChunk;

	public long getTotalDeRegistros() {
		return totalDeRegistros;
	}

	public void setTotalDeRegistros(long totalDeRegistros) {
		this.totalDeRegistros = totalDeRegistros;
	}

	public int getTamanhoDoChunk() {
		return tamanhoDoChunk;
	}

	public void setTamanhoDoChunk(int tamanhoDoChunk) {
		this.tamanhoDoChunk = tamanhoDoChunk;
	}
}
