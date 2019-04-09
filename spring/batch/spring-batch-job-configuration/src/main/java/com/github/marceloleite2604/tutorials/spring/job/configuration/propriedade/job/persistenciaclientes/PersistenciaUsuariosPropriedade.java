package com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.job.persistenciaclientes;

import com.github.marceloleite2604.tutorials.spring.job.configuration.model.Propriedade;
import com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.job.CriadorUsuariosPropriedade;

public enum PersistenciaUsuariosPropriedade implements Propriedade {

	QUANTIDADE_LEITORES("quantidade-de-leitores"),
	TAMANHO_DO_CHUNK("tamanho-do-chunk");

	public static final String PREFIXO_PROPRIEDADES = CriadorUsuariosPropriedade.PREFIXO_PROPRIEDADES
			+ "persistir-clientes.";

	private String nome;

	private String valorPadrao;

	private PersistenciaUsuariosPropriedade(String nome, String valorPadrao) {
		this.nome = nome;
		this.valorPadrao = valorPadrao;
	}

	private PersistenciaUsuariosPropriedade(String nome) {
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
