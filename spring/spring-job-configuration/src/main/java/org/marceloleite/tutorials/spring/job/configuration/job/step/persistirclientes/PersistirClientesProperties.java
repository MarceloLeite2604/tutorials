package org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("meu-job.persistir-clientes")
public class PersistirClientesProperties {

	private int quantidadeDeLeitores;

	private int tamanhoDoChunk;

	public int getQuantidadeDeLeitores() {
		return quantidadeDeLeitores;
	}

	public void setQuantidadeDeLeitores(int quantidadeDeLeitores) {
		this.quantidadeDeLeitores = quantidadeDeLeitores;
	}

	public int getTamanhoDoChunk() {
		return tamanhoDoChunk;
	}

	public void setTamanhoDoChunk(int tamanhoDoChunk) {
		this.tamanhoDoChunk = tamanhoDoChunk;
	}
}
