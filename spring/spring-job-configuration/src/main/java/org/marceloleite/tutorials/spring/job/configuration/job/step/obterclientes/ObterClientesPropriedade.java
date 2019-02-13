package org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes;

import org.marceloleite.tutorials.spring.job.configuration.job.MeuJobPropriedade;
import org.marceloleite.tutorials.spring.job.configuration.model.propriedades.Propriedade;

public enum ObterClientesPropriedade implements Propriedade {

	TOTAL_DE_REGISTROS("total-de-registros"),
	TAMANHO_DO_CHUNK("tamanho-do-chunk");

	private static final String PREFIXO_PROPRIEDADES = MeuJobPropriedade.PREFIXO_PROPRIEDADES
			+ "obterClientes.";

	private ObterClientesPropriedade(String nome, String valorPadrao) {
		this.nome = nome;
		this.valorPadrao = valorPadrao;
	}

	private ObterClientesPropriedade(String nome) {
		this(nome, null);
	}

	private String nome;

	private String valorPadrao;

	@Override
	public boolean isObrigatorio() {
		return (valorPadrao == null);
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
	public String getNomeCompleto() {
		return PREFIXO_PROPRIEDADES + nome;
	}
}
