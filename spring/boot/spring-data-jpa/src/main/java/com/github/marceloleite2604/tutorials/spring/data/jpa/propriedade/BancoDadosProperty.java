package com.github.marceloleite2604.tutorials.spring.data.jpa.propriedade;

import com.github.marceloleite2604.properties.model.Property;
import com.github.marceloleite2604.tutorials.spring.data.jpa.DefinicoesPrograma;

public enum BancoDadosProperty implements Property {

	DRIVER_CLASS_NAME("driver-class-name"),
	URL("url"),
	USUARIO("usuario"),
	SENHA("senha");

	private BancoDadosProperty(String nome, String valorPadrao) {
		this.name = nome;
		this.defaultValue = valorPadrao;
	}

	private BancoDadosProperty(String nome) {
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
		return DefinicoesPrograma.PREFIXO_PROPRIEDADES_BANCO_DADOS + "." + name;
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
