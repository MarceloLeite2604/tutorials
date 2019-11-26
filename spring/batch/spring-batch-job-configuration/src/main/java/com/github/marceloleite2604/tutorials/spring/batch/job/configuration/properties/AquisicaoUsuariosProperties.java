package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(CaminhosProperties.AQUISICAO_USUARIOS)
public class AquisicaoUsuariosProperties {

	/**
	 * Tamanho do lote de usuários escritos no arquivo.
	 */
	private int tamanhoDoLote;

	public int getTamanhoDoLote() {
		return tamanhoDoLote;
	}

	public void setTamanhoDoLote(int tamanhoDoLote) {
		this.tamanhoDoLote = tamanhoDoLote;
	}
}
