package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class UsuarioPO {

	@Id
	@Column(name = "ID")
	private String uuid;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "SOBRENOME")
	private String sobrenome;

	@Column(name = "USUARIO")
	private String usuario;
	
	private UsuarioPO() {
	}

	private UsuarioPO(Builder builder) {
		this.uuid = builder.uuid;
		this.nome = builder.nome;
		this.sobrenome = builder.sobrenome;
		this.usuario = builder.usuario;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String uuid;
		private String nome;
		private String sobrenome;
		private String usuario;

		private Builder() {
		}

		public Builder uuid(String uuid) {
			this.uuid = uuid;
			return this;
		}

		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder sobrenome(String sobrenome) {
			this.sobrenome = sobrenome;
			return this;
		}

		public Builder usuario(String usuario) {
			this.usuario = usuario;
			return this;
		}

		public UsuarioPO build() {
			return new UsuarioPO(this);
		}
	}
}
