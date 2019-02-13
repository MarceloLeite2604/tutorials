package org.marceloleite.tutorials.spring.job.configuration.job;

import org.marceloleite.tutorials.spring.job.configuration.model.propriedades.Propriedade;

public enum MeuJobPropriedade implements Propriedade {

	CAMINHO_ARQUIVO_DE_USUARIOS("caminho-arquivo-de-usuarios"),
	TOTAL_DE_REGISTROS("total-de-registros");

	public static final String PREFIXO_PROPRIEDADES = "meu-job.";

	private String nome;

	private String valorPadrao;

	private MeuJobPropriedade(String nome, String valorPadrao) {
		this.nome = nome;
		this.valorPadrao = valorPadrao;
	}

	private MeuJobPropriedade(String nome) {
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
