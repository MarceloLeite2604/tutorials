package br.com.alura.aula2.exercicio;

import br.com.alura.geral.Conta;

public class RespostaPorcento implements Resposta {

	public RespostaPorcento(Resposta resposta) {
		super();
	}

	@Override
	public void responde(Requisicao requisicao, Conta conta) {
		System.out.println(conta.getTitular() + "%" + conta.getSaldo());
	}

}
