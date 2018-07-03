package br.com.alura.aula2.exercicio;

import br.com.alura.geral.Conta;

public class TesteCadeiaDeRespostas {

	public static void main(String[] args) {
		Conta conta = Conta.builder()
				.titular("João")
				.saldo(100.0)
				.build();

		Requisicao requisicao = new Requisicao(Formato.PORCENTO);

		new CadeiaDeRespostas().responder(requisicao, conta);
	}
}
