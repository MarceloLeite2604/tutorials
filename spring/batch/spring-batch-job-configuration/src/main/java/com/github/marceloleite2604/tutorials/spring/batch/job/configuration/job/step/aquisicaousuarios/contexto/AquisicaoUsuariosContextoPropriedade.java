package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.aquisicaousuarios.contexto;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.DefinicoesJobCriacaoUsuarios;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.Propriedade;

public enum AquisicaoUsuariosContextoPropriedade implements Propriedade {

	REGISTROS_LIDOS("registros-lidos", "0"),
	REGISTROS_ESCRITOS("registros-escritos", "0"),
	CAMINHO_ARQUIVO_DE_USUARIOS("caminho-arquivo-de-usuarios"),
	TOTAL_DE_REGISTROS("total-de-registros");

	private static final String PREFIXO_PROPRIEDADES = DefinicoesJobCriacaoUsuarios.NOME_STEP_AQUISICAO_USUARIOS
			+ ".";

	private AquisicaoUsuariosContextoPropriedade(String nome, String valorPadrao) {
		this.nome = nome;
		this.valorPadrao = valorPadrao;
	}

	private AquisicaoUsuariosContextoPropriedade(String nome) {
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
