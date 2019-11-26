package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties;

import javax.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(CaminhosProperties.CRIPTOGRAFIA)
@Validated
public class CriptografiaProperties {

	/**
	 * Algoritmo utilizado para a criptografia de dados.
	 */
	@NotBlank
	private String algoritmoCriptografia;

	/**
	 * Modo de feedback utilizado na criptografia de dados.
	 */
	@NotBlank
	private String modoFeedback;

	/**
	 * Modo de preenchimento utilizado na criptografia de dados.
	 */
	@NotBlank
	private String modoPreenchimento;

	/**
	 * Chave utilizada para a criptografia de dados.
	 */
	private String chave;

	/**
	 * Nome da variável de ambiente que contém a criptografia de dados.
	 */
	private String nomeVariavelAmbienteChave;

	public String getAlgoritmoCriptografia() {
		return algoritmoCriptografia;
	}

	public void setAlgoritmoCriptografia(String algoritmoCriptografia) {
		this.algoritmoCriptografia = algoritmoCriptografia;
	}

	public String getModoFeedback() {
		return modoFeedback;
	}

	public void setModoFeedback(String modoFeedback) {
		this.modoFeedback = modoFeedback;
	}

	public String getModoPreenchimento() {
		return modoPreenchimento;
	}

	public void setModoPreenchimento(String modoPreenchimento) {
		this.modoPreenchimento = modoPreenchimento;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getNomeVariavelAmbienteChave() {
		return nomeVariavelAmbienteChave;
	}

	public void setNomeVariavelAmbienteChave(String nomeVariavelAmbienteChave) {
		this.nomeVariavelAmbienteChave = nomeVariavelAmbienteChave;
	}
}