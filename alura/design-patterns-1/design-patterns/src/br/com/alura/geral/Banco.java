package br.com.alura.geral;

import java.util.List;
import java.util.Collections;

public class Banco {

	private String nome;

	private String telefone;

	private String endereco;

	private String email;

	private List<Conta> contas;

	private Banco(Builder builder) {
		this.nome = builder.nome;
		this.telefone = builder.telefone;
		this.endereco = builder.endereco;
		this.email = builder.email;
		this.contas = builder.contas;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getEmail() {
		return email;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String nome;
		private String telefone;
		private String endereco;
		private String email;
		private List<Conta> contas = Collections.emptyList();

		private Builder() {
		}

		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder telefone(String telefone) {
			this.telefone = telefone;
			return this;
		}

		public Builder endereco(String endereco) {
			this.endereco = endereco;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder contas(List<Conta> contas) {
			this.contas = contas;
			return this;
		}

		public Banco build() {
			return new Banco(this);
		}
	}

}
