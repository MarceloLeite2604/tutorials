package org.marceloleite.tutorials.spring.hsqldb.model;

import java.io.Serializable;
import java.util.UUID;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;

	private long valor;
	private Cliente(Builder builder) {
		this.id = builder.id;
		this.valor = builder.valor;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (valor ^ (valor >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", valor=" + valor + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private UUID id;
		private long valor;

		private Builder() {
		}

		public Builder id(UUID id) {
			this.id = id;
			return this;
		}

		public Builder valor(long valor) {
			this.valor = valor;
			return this;
		}

		public Cliente build() {
			return new Cliente(this);
		}
	}
	
	
}
