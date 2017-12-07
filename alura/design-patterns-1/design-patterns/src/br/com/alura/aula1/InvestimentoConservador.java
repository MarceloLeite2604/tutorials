package br.com.alura.aula1;

import java.util.Random;

public class InvestimentoConservador implements Investimento {

	private static final double LUCRO = 0.008;

	private static final double PROBABILIDADE = 1.0;

	@Override
	public double investir(ContaBancaria contaBancaria) {
		double resultado = contaBancaria.getValor() * LUCRO * (new Random().nextDouble() < PROBABILIDADE ? 1.0 : 0.0);
		System.out.println("Investimento conservador: R$ " + resultado + ".");
		return resultado;
	}

}
