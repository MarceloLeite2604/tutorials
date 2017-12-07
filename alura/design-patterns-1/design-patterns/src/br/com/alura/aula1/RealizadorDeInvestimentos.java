package br.com.alura.aula1;

public class RealizadorDeInvestimentos {

	private static final double PORCENTAGEM_DE_RETORNO_AO_CLIENTE = 0.75;

	public void calcular(ContaBancaria contaBancaria, Investimento investimento) {
		double lucro = investimento.investir(contaBancaria);
		System.out.println("O investimento retornou R$ " + lucro + " de lucro.");
		double retornoAoCliente = lucro * PORCENTAGEM_DE_RETORNO_AO_CLIENTE;
		System.out.println("Será retornado R$ " + retornoAoCliente + " ao cliente.");
		contaBancaria.setValor(contaBancaria.getValor() + retornoAoCliente);
	}
}
