package org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes.contexto;

import org.marceloleite.tutorials.spring.job.configuration.model.propriedades.Propriedade;

public enum PersistirClientesContextoPropriedade implements Propriedade {

	CAMINHO_ARQUIVO_DE_USUARIOS("caminho-arquivo-de-usuarios"),
	REGISTROS_ESCRITOS("registros-escritos", "0"),
	TOTAL_DE_REGISTROS("total-de-registros");

	private static final String PREFIXO_PARAMETROS = "persistir-clientes.contexto.";

	private String nome;

	private String valorPadrao;

	private PersistirClientesContextoPropriedade(String nome, String valorPadrao) {
		this.nome = nome;
		this.valorPadrao = valorPadrao;
	}

	private PersistirClientesContextoPropriedade(String nome) {
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
		return (valorPadrao != null);
	}

	@Override
	public String getNomeCompleto() {
		return PREFIXO_PARAMETROS + nome;
	}
}
