package br.com.alura.aula2.exercicio;

public class TesteCadeiaDeRespostas {

	public static void main(String[] args) {
		Conta conta = new Conta("João", 100.0);
		
		Requisicao requisicao = new Requisicao(Formato.PORCENTO);
		
		new CadeiaDeRespostas().responder(requisicao, conta);
	}
}
