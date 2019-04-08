package com.github.marceloleite2604.tutorials.spring.data.jpa.propriedade;

import com.github.marceloleite2604.properties.model.Property;
import com.github.marceloleite2604.tutorials.spring.data.jpa.DefinicoesPrograma;

public enum CriptografiaProperty implements Property {

	ALGORITMO("algoritmo", "DESede"),
	MODO_FEEDBACK("modo-feedback", "CBC"),
	MODO_PREENCHIMENTO("modo-preenchimento", "PKCS5Padding"),
	CHAVE("chave");

	private CriptografiaProperty(String nome, String valorPadrao) {
		this.name = nome;
		this.defaultValue = valorPadrao;
	}

	private CriptografiaProperty(String nome) {
		this(nome, null);
	}

	private String name;

	private String defaultValue;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getCompleteName() {
		return DefinicoesPrograma.PREFIXO_PROPRIEDADES_CRIPTOGRAFIA + "." + name;
	}

	@Override
	public String getDefaultValue() {
		return defaultValue;
	}

	@Override
	public boolean isMandatory() {
		return (defaultValue == null);
	}

}
