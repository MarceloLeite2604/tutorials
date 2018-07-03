package br.com.alura.geral;

public class ContaFixture {

	public static Conta criarConta100Reais() {
		return Conta.builder()
				.titular("João")
		.saldo(100.0)
		.build();
	}
}
