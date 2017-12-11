package br.com.alura.aula2.exercicio;

public class RespostaPorcento implements Resposta {
	
	public RespostaPorcento(Resposta resposta) {
		super();
	}

	@Override
	public void responde(Requisicao requisicao, Conta conta) {
		System.out.println(conta.getNome() + "%" + conta.getSaldo());
	}

}
