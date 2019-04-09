package com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.job.aquisicaousuarios;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.CaminhosPropriedades;

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
