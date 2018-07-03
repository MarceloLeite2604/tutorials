package br.com.alura.aula1;

import br.com.alura.geral.Conta;
import br.com.alura.geral.ContaFixture;

public class TesteDeInvestimentos {

	public static void main(String[] args) {

		Investimento investimentoConservador = new InvestimentoConservador();
		Investimento investimentoModerado = new InvestimentoModerado();
		Investimento investimentoArrojado = new InvestimentoArrojado();

		Conta conta = ContaFixture.criarConta100Reais();

		RealizadorDeInvestimentos realizadorDeInvestimentos = new RealizadorDeInvestimentos();

		realizadorDeInvestimentos.calcular(conta, investimentoConservador);
		realizadorDeInvestimentos.calcular(conta, investimentoModerado);
		realizadorDeInvestimentos.calcular(conta, investimentoArrojado);
	}
}
