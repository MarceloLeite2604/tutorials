package br.com.alura.geral;

import java.time.LocalDateTime;

public class Conta {

	private String titular;
	
	private String agencia;
	
	private String conta;
	
	private double saldo;
	
	private LocalDateTime dataAbertura;

	private Conta(Builder builder) {
		this.titular = builder.titular;
		this.agencia = builder.agencia;
		this.conta = builder.conta;
		this.saldo = builder.saldo;
		this.dataAbertura = builder.dataAbertura;
	}

	public String getTitular() {
		return titular;
	}

	public String getAgencia() {
		return agencia;
	}

	public String getConta() {
		return conta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String titular;
		private String agencia;
		private String conta;
		private double saldo;
		private LocalDateTime dataAbertura;

		private Builder() {
		}

		public Builder titular(String titular) {
			this.titular = titular;
			return this;
		}

		public Builder agencia(String agencia) {
			this.agencia = agencia;
			return this;
		}

		public Builder conta(String conta) {
			this.conta = conta;
			return this;
		}

		public Builder saldo(double saldo) {
			this.saldo = saldo;
			return this;
		}

		public Builder dataAbertura(LocalDateTime dataAbertura) {
			this.dataAbertura = dataAbertura;
			return this;
		}

		public Conta build() {
			return new Conta(this);
		}
	}
}
