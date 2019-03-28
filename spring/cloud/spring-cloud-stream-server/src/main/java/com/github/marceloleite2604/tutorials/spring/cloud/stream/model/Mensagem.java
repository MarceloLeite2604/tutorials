package com.github.marceloleite2604.tutorials.spring.cloud.stream.model;

import java.util.UUID;

public class Mensagem {

	private UUID uuid;

	private String texto;
	
	public Mensagem() {
	}

	public Mensagem(String texto) {
		this.texto = texto;
		this.uuid = UUID.randomUUID();
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
