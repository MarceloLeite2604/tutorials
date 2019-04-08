package com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade;

import com.github.marceloleite2604.tutorials.spring.job.configuration.model.Propriedade;

public enum CriptografiaPropriedade implements Propriedade {
	ALGORITMO_CRIPTOGRAFIA("algoritmo-criptografia", "DESede"),
	MODO_FEEDBACK("modo-feedback", "CBC"),
	MODO_PREENCHIMENTO("modo-preenchimento", "PKCS5Padding"),
	CHAVE("chave");

	private CriptografiaPropriedade(String nome, String valorPadrao) {
		this.nome = nome;
		this.valorPadrao = valorPadrao;
	}

	private CriptografiaPropriedade(String nome) {
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
		return nome;
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
