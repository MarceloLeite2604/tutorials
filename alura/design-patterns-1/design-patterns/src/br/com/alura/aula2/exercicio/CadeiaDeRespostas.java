package br.com.alura.aula2.exercicio;

import br.com.alura.geral.Conta;

public class CadeiaDeRespostas {

	public void responder(Requisicao requisicao, Conta conta) {
		Resposta r3 = new RespostaPorcento(null);
		Resposta r2 = new RespostaXML(r3);
		Resposta r1 = new RespostaCSV(r2);
		
		r1.responde(requisicao, conta);
	}
}
