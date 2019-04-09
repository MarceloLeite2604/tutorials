package com.github.marceloleite2604.tutorials.spring.job.configuration.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class UsuarioCsvVO {

	@CsvBindByName(column = "UUID")
	@CsvBindByPosition(position = 0)
	private String uuid;

	@CsvBindByName(column = "NOME")
	@CsvBindByPosition(position = 1)
	private String nome;

	@CsvBindByName(column = "SOBRENOME")
	@CsvBindByPosition(position = 2)
	private String sobrenome;

	@CsvBindByName(column = "USUARIO")
	@CsvBindByPosition(position = 3)
	private String usuario;

	private UsuarioCsvVO(Builder builder) {
		this.uuid = builder.uuid;
		this.nome = builder.nome;
		this.sobrenome = builder.sobrenome;
		this.usuario = builder.usuario;
	}

	public UsuarioCsvVO() {
		// Construtor padrão para deserialização de objetos.
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

	@Override
	public String toString() {
		return "UsuarioCsvVO [uuid=" + uuid + ", nome=" + nome + ", sobrenome=" + sobrenome
				+ ", usuario=" + usuario + "]";
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

		public UsuarioCsvVO build() {
			return new UsuarioCsvVO(this);
		}
	}
}
