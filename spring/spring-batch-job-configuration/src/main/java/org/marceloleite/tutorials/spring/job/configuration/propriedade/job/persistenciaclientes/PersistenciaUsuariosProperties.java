package org.marceloleite.tutorials.spring.job.configuration.propriedade.job.persistenciaclientes;

import org.marceloleite.tutorials.spring.job.configuration.propriedade.CaminhosPropriedades;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(CaminhosPropriedades.PERSISTENCIA_USUARIOS)
public class PersistenciaUsuariosProperties {

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
