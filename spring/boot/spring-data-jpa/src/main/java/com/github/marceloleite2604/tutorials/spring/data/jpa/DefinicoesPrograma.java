package com.github.marceloleite2604.tutorials.spring.data.jpa;

public final class DefinicoesPrograma {

	private static final String PREFIXO_PROPRIEDADES_PROGRAMA = "programa";

	public static final String PREFIXO_PROPRIEDADES_BANCO_DADOS = PREFIXO_PROPRIEDADES_PROGRAMA
			+ "." + "banco-dados";

	public static final String PREFIXO_PROPRIEDADES_CRIPTOGRAFIA = PREFIXO_PROPRIEDADES_PROGRAMA
			+ "." + "criptografia";

	private DefinicoesPrograma() {
		// Construtor privado para evitar a criação de objetos desta classe.
	}
}
