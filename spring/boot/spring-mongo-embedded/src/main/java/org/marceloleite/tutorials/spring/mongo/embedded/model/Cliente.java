package org.marceloleite.tutorials.spring.mongo.embedded.model;

import java.util.UUID;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="fs.files")
public class Cliente {

	@Id
	private UUID id;

	private long valor;

	public Cliente() {

	}

	@Generated("SparkTools")
	private Cliente(Builder builder) {
		this.id = builder.id;
		this.valor = builder.valor;
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
	public String toString() {
		return "Cliente [id=" + id + ", valor=" + valor + "]";
	}

	/**
	 * Creates builder to build {@link Cliente}.
	 * 
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Cliente}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private UUID id;
		private long valor;

		private Builder() {
		}

		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		public Builder withValor(long valor) {
			this.valor = valor;
			return this;
		}

		public Cliente build() {
			return new Cliente(this);
		}
	}

}
