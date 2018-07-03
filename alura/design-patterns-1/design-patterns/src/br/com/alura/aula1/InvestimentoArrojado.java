package br.com.alura.aula1;

import java.util.Random;

import br.com.alura.geral.Conta;

public class InvestimentoArrojado implements Investimento {

	private static final int INDICE_PROBABLIDADE = 0;
	private static final int INDICE_LUCRO = 1;
	private static final double[][] LUCROS = { { 0.2, 0.05 }, { 0.5, 0.03 }, { 1.0, 0.06 } };

	@Override
	public double investir(Conta conta) {
		double resultado = conta.getSaldo() * calcularInvestimento();
		System.out.println("Resultado do investimento: " + resultado);
		return resultado;
	}

	private double calcularInvestimento() {
		double valorAleatorio = new Random().nextDouble();
		for (double[] lucro : LUCROS) {
			if (valorAleatorio <= lucro[INDICE_PROBABLIDADE]) {
				System.out.println(
						"Valor aleatório: " + valorAleatorio + ". Porcentagem de lucro: " + lucro[INDICE_LUCRO] + ".");
				return lucro[INDICE_LUCRO];
			}
		}

		throw new IllegalStateException("Erro ao calcular a probabilidade do investimento.");
	}

}
