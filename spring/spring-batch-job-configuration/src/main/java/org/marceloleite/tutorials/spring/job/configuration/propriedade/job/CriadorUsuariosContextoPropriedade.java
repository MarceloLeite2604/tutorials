package org.marceloleite.tutorials.spring.job.configuration.propriedade.job;

import org.marceloleite.tutorials.spring.job.configuration.model.Propriedade;

public enum CriadorUsuariosContextoPropriedade implements Propriedade {
	INSTANTE("instante");

	private static final String PREFIXO_PROPRIEDADES = "criador-usuarios.";

	private CriadorUsuariosContextoPropriedade(String nome, String valorPadrao) {
		this.nome = nome;
		this.valorPadrao = valorPadrao;
	}

	private CriadorUsuariosContextoPropriedade(String nome) {
		this(nome, null);
	}

	private String nome;

	private String valorPadrao;

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public String getNomeCompleto() {
		return PREFIXO_PROPRIEDADES + nome;
	}

	@Override
	public String getValorPadrao() {
		return valorPadrao;
	}

	@Override
	public boolean isObrigatorio() {
		return (valorPadrao == null);
	}

}
