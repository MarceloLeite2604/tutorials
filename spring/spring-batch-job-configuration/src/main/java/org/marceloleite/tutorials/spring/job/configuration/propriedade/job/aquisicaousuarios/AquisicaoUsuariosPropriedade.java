package org.marceloleite.tutorials.spring.job.configuration.propriedade.job.aquisicaousuarios;

import org.marceloleite.tutorials.spring.job.configuration.model.Propriedade;
import org.marceloleite.tutorials.spring.job.configuration.propriedade.job.CriadorUsuariosPropriedade;

public enum AquisicaoUsuariosPropriedade implements Propriedade {

	TOTAL_DE_REGISTROS("total-de-registros"),
	TAMANHO_DO_CHUNK("tamanho-do-chunk");

	private static final String PREFIXO_PROPRIEDADES = CriadorUsuariosPropriedade.PREFIXO_PROPRIEDADES
			+ "obterClientes.";

	private AquisicaoUsuariosPropriedade(String nome, String valorPadrao) {
		this.nome = nome;
		this.valorPadrao = valorPadrao;
	}

	private AquisicaoUsuariosPropriedade(String nome) {
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
