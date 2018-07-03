package br.com.alura.aula2.exercicio;

import br.com.alura.geral.Conta;

public class RespostaCSV implements Resposta {

	private Resposta resposta;

	public RespostaCSV(Resposta resposta) {
		super();
		this.resposta = resposta;
	}

	@Override
	public void responde(Requisicao requisicao, Conta conta) {
		if (requisicao.getFormato() == Formato.CSV) {
			System.out.println(conta.getTitular() + "," + conta.getSaldo());
		} else {
			resposta.responde(requisicao, conta);
		}

	}
}
