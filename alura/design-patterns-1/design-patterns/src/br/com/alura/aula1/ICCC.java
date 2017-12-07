package br.com.alura.aula1;

public class ICCC implements Imposto {

	@Override
	public double calcula(Orcamento orcamento) {
		double porcentagem;
		double valor = 0.0;
		if (orcamento.getValor() < 1000) {
			porcentagem = 0.05;
		} else if (orcamento.getValor() <= 3000) {
			porcentagem = 0.07;
		} else {
			porcentagem = 0.08;
			valor = 30.0;
		}

		return (orcamento.getValor() * porcentagem) + valor;
	}

}
