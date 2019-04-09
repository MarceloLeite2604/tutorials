package com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade;

import com.github.marceloleite2604.tutorials.spring.job.configuration.job.DefinicoesJobCriacaoUsuarios;

public final class CaminhosPropriedades {

	private static final String BANCO = "banco";
	
	public static final String BANCO_SPRING_BATCH = BANCO
	+ ".spring-batch";
	
	public static final String BANCO_PROGRAMA = BANCO
	+ ".programa";
	
	public static final String CRIPTOGRAFIA = "criptografia";
	
	public static final String CRIADOR_USUARIOS = DefinicoesJobCriacaoUsuarios.NOME_DO_JOB;
	
	public static final String AQUISICAO_USUARIOS = CRIADOR_USUARIOS
	+ "." + DefinicoesJobCriacaoUsuarios.NOME_STEP_AQUISICAO_USUARIOS;
	
	public static final String PERSISTENCIA_USUARIOS = CRIADOR_USUARIOS
	+ "." + DefinicoesJobCriacaoUsuarios.NOME_STEP_PERSISTENCIA_USUARIOS;

	private CaminhosPropriedades() {
		// Construtor privado para evitar a criação de objetos.
	}

}
