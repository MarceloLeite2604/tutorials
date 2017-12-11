package br.com.alura.aula2;

import br.com.alura.aula1.Orcamento;

/*
 * Chain of responsibility: Quando devemos realizar porocessos baseados em um
 * conjunto de regras. Cada um dos pares "regra, processo" pode ficar armazenado
 * em uma classe separada. Cada uma das classes verifica se a sua regra foi
 * atendida. Caso não tenha sido, então passa a responsabilidade para a próxima
 * classe "regra, processo" armazenada.
 */
public class TesteDeDescontos {

	public static void main(String[] args) {
		CalculadorDeDescontos calculadorDeDescontos = new CalculadorDeDescontos();

		Orcamento orcamento = new Orcamento(500.0);
		orcamento.adicionaItem(new Item("Caneta", 250.0));
		orcamento.adicionaItem(new Item("Lápis", 250.0));

		double descontoFinal = calculadorDeDescontos.calcula(orcamento);

		System.out.println(descontoFinal);
	}
}
