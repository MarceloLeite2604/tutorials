package br.com.alura.aula1;

public class TesteDeInvestimentos {

	public static void main(String[] args) {

		Investimento investimentoConservador = new InvestimentoConservador();
		Investimento investimentoModerado = new InvestimentoModerado();
		Investimento investimentoArrojado = new InvestimentoArrojado();

		ContaBancaria contaBancaria = new ContaBancaria(100.0);

		RealizadorDeInvestimentos realizadorDeInvestimentos = new RealizadorDeInvestimentos();
		
		realizadorDeInvestimentos.calcular(contaBancaria, investimentoConservador);
		realizadorDeInvestimentos.calcular(contaBancaria, investimentoModerado);
		realizadorDeInvestimentos.calcular(contaBancaria, investimentoArrojado);
	}
}
