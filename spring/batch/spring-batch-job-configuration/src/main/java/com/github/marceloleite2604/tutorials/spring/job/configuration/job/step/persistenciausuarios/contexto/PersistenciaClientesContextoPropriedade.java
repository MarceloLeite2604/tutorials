package com.github.marceloleite2604.tutorials.spring.job.configuration.job.step.persistenciausuarios.contexto;

import com.github.marceloleite2604.tutorials.spring.job.configuration.job.DefinicoesJobCriacaoUsuarios;
import com.github.marceloleite2604.tutorials.spring.job.configuration.model.Propriedade;

public enum PersistenciaClientesContextoPropriedade implements Propriedade {

	CAMINHO_ARQUIVO_DE_USUARIOS("caminho-arquivo-de-usuarios"),
	REGISTROS_ESCRITOS("registros-escritos", "0"),
	TOTAL_DE_REGISTROS("total-de-registros");

	private static final String PREFIXO_PARAMETROS = DefinicoesJobCriacaoUsuarios.NOME_STEP_PERSISTENCIA_USUARIOS
			+ ".";

	private String nome;

	private String valorPadrao;

	private PersistenciaClientesContextoPropriedade(String nome, String valorPadrao) {
		this.nome = nome;
		this.valorPadrao = valorPadrao;
	}

	private PersistenciaClientesContextoPropriedade(String nome) {
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
