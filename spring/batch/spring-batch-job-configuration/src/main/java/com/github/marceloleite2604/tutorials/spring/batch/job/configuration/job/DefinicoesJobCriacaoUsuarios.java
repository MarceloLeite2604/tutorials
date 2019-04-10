package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job;

public final class DefinicoesJobCriacaoUsuarios {

	public static final String NOME_DO_JOB = "criador-usuarios";

	public static final String NOME_STEP_PREPARO_AMBIENTE = "preparo-ambiente";

	public static final String NOME_STEP_AQUISICAO_USUARIOS = "aquisicao-usuarios";
	
	public static final String NOME_STEP_PERSISTENCIA_USUARIOS = "persistencia-usuarios";

	public static final String NOME_STEP_PERSISTENCIA_USUARIOS_MANAGER = NOME_STEP_PERSISTENCIA_USUARIOS
			+ "-manager";

	public static final String NOME_STEP_PERSISTENCIA_USUARIOS_WORKER = NOME_STEP_PERSISTENCIA_USUARIOS
			+ "-worker";

	public static final String NOME_STEP_LIMPEZA_AMBIENTE = "limpeza-ambiente";


	private DefinicoesJobCriacaoUsuarios() {
		// Construtor privado para evitar a criação de objetos desta classe.
	}
}
