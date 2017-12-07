package br.com.alura.aula1;

import java.util.Random;

public class InvestimentoModerado implements Investimento {

	private static final int INDICE_PROBABLIDADE = 0;
	private static final int INDICE_LUCRO = 1;
	private static final double[][] LUCROS = { { 0.5, 0.025 }, { 1, 0.07 } };

	@Override
	public double investir(ContaBancaria contaBancaria) {
		double resultado = contaBancaria.getValor() * calcularInvestimento();
		System.out.println("Resultado do investimento: " + resultado);
		return resultado;
	}

	private double calcularInvestimento() {
		double valorAleatorio = new Random().nextDouble();
		for (double[] lucro : LUCROS) {
			if (valorAleatorio <= lucro[INDICE_PROBABLIDADE]) {
				System.out.println(
						"Valor aleatório: " + valorAleatorio + ". Porcentagem de lucro: " + lucro[INDICE_LUCRO]*100 + "%.");
				return lucro[INDICE_LUCRO];
			}
		}

		throw new IllegalStateException("Erro ao calcular a probabilidade do investimento.");
	}

}
