package org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("meu-job.obter-clientes")
public class ObterClientesProperties {

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
