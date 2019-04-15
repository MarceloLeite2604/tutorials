package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.job;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.Propriedade;

public enum CriadorUsuariosPropriedade implements Propriedade {

	DIRETORIO_ARQUIVO_DE_USUARIOS("diretorio-arquivo-de-usuarios"),
	TOTAL_DE_REGISTROS("total-de-registros");

	public static final String PREFIXO_PROPRIEDADES = "criador-usuarios.";

	private String nome;

	private String valorPadrao;

	private CriadorUsuariosPropriedade(String nome, String valorPadrao) {
		this.nome = nome;
		this.valorPadrao = valorPadrao;
	}

	private CriadorUsuariosPropriedade(String nome) {
		this(nome, null);
	}

	@Override
	public String getNome() {
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

	@Override
	public String getNomeCompleto() {
		return PREFIXO_PROPRIEDADES + nome;
	}
}