package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.diversos;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.Propriedade;

public enum ArgumentoPrograma implements Propriedade {
	INSTANTE("instante");

	private static final String PREFIXO_ARGUMENTOS = "--";

	private ArgumentoPrograma(String nome, String valorPadrao) {
		this.nome = nome;
		this.valorPadrao = valorPadrao;
	}

	private ArgumentoPrograma(String nome) {
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
		return PREFIXO_ARGUMENTOS + nome;
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
