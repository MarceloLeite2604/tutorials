package br.com.alura.aula1;

import br.com.alura.geral.Conta;

public class RealizadorDeInvestimentos {

	private static final double PORCENTAGEM_DE_RETORNO_AO_CLIENTE = 0.75;

	public void calcular(Conta conta, Investimento investimento) {
		double lucro = investimento.investir(conta);
		System.out.println("O investimento retornou R$ " + lucro + " de lucro.");
		double retornoAoCliente = lucro * PORCENTAGEM_DE_RETORNO_AO_CLIENTE;
		System.out.println("Será retornado R$ " + retornoAoCliente + " ao cliente.");
		conta.setSaldo(conta.getSaldo() + retornoAoCliente);
	}
}
