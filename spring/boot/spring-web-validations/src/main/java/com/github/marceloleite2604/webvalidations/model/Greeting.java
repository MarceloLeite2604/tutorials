package com.github.marceloleite2604.webvalidations.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.github.marceloleite2604.webvalidations.model.validation.database.GreetingDatabaseValidation;
import com.github.marceloleite2604.webvalidations.model.validation.rest.GreetingRestValidation;

@Entity(name="GREETING")
public class Greeting {

	@Id
	@Column(name="ID")
	@NotNull(groups = GreetingDatabaseValidation.class, message = "Greeting ID cannot be null.")
	private Long id;

	@Column(name="CONTENT")
	@NotNull(groups = { GreetingDatabaseValidation.class,
			GreetingRestValidation.class }, message = "Greeting content cannot be null.")
	private String content;

	public Greeting() {
		// Construtor padrão para deserialização de objetos.
	}

	private Greeting(Builder builder) {
		this.id = builder.id;
		this.content = builder.content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private long id;
		private String content;

		private Builder() {
		}

		public Builder id(long id) {
			this.id = id;
			return this;
		}

		public Builder content(String content) {
			this.content = content;
			return this;
		}

		public Greeting build() {
			return new Greeting(this);
		}
	}

}