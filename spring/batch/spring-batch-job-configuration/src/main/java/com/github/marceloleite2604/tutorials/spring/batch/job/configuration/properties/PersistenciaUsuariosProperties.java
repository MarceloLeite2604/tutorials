package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties;

import javax.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(CaminhosProperties.PERSISTENCIA_USUARIOS)
public class PersistenciaUsuariosProperties {

	/**
	 * Quantidade de processos executando em paralelo e realizando a persistência de
	 * usuários no banco.
	 */
	@Min(1)
	private int quantidadeDeProcessos;

	/**
	 * Tamanho do lote de usuários persistidos no banco por leitura.
	 */
	@Min(1)
	private int tamanhoDoLote;

	public int getQuantidadeDeProcessos() {
		return quantidadeDeProcessos;
	}

	public void setQuantidadeDeProcessos(int quantidadeDeProcessos) {
		this.quantidadeDeProcessos = quantidadeDeProcessos;
	}

	public int getTamanhoDoLote() {
		return tamanhoDoLote;
	}

	public void setTamanhoDoLote(int tamanhoDoLote) {
		this.tamanhoDoLote = tamanhoDoLote;
	}
}
