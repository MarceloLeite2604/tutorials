package org.marceloleite.tutorials.redis.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Student")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;

	private long valor;

	public Cliente(UUID id, long valor) {
		super();
		this.id = id;
		this.valor = valor;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public long getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}
}
