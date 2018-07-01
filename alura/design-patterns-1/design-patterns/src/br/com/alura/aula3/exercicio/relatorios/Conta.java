package br.com.alura.aula3.exercicio.relatorios;

public class Conta {

	private String titular;
	
	private String agencia;
	
	private String conta;
	
	private double saldo;
	
	

	public Conta(String titular, String agencia, String conta, double saldo) {
		super();
		this.titular = titular;
		this.agencia = agencia;
		this.conta = conta;
		this.saldo = saldo;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
}
