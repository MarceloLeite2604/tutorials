package org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes;

import org.marceloleite.tutorials.spring.job.configuration.job.MeuJobPropriedade;
import org.marceloleite.tutorials.spring.job.configuration.model.propriedades.Propriedade;

public enum PersistirClientesPropriedade implements Propriedade {

	QUANTIDADE_LEITORES("quantidade-de-leitores"),
	TAMANHO_DO_CHUNK("tamanho-do-chunk");

	public static final String PREFIXO_PROPRIEDADES = MeuJobPropriedade.PREFIXO_PROPRIEDADES
			+ "persistir-clientes.";

	private String nome;

	private String valorPadrao;

	private PersistirClientesPropriedade(String nome, String valorPadrao) {
		this.nome = nome;
		this.valorPadrao = valorPadrao;
	}

	private PersistirClientesPropriedade(String nome) {
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
