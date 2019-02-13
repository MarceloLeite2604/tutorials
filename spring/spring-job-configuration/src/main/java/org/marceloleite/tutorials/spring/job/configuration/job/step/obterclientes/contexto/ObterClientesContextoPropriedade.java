package org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.contexto;

import org.marceloleite.tutorials.spring.job.configuration.model.propriedades.Propriedade;

public enum ObterClientesContextoPropriedade implements Propriedade {

	REGISTROS_ESCRITOS("registros-escritos", "0"),
	CAMINHO_ARQUIVO_DE_USUARIOS("caminho-arquivo-de-usuarios"),
	TOTAL_DE_REGISTROS("total-de-registros");

	private static final String PREFIXO_PROPRIEDADES = "obter-clientes.contexto.";

	private ObterClientesContextoPropriedade(String nome, String valorPadrao) {
		this.nome = nome;
		this.valorPadrao = valorPadrao;
	}

	private ObterClientesContextoPropriedade(String nome) {
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
