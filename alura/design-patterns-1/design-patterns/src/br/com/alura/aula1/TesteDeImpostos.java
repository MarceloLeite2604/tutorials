package br.com.alura.aula1;

public class TesteDeImpostos {

	/*
	 * Strategy pattern: Quando utilizamos uma interface que define um método
	 * genérico. As classes que implementam esta interface define como deve ser
	 * o método a ser executado. Já a classe que utiliza esta interface
	 * necessita somente a execução de um método, independente da sua
	 * implementação.
	 */
	public static void main(String[] args) {
		Imposto iss = new ISS();

		Imposto icms = new ICMS();
		
		Imposto iccc = new ICCC();

		Orcamento orcamento = new Orcamento(3000.0);

		CalculadorDeImpostos calculadorDeImpostos = new CalculadorDeImpostos();

		calculadorDeImpostos.realizaCalculo(orcamento, iss);
		calculadorDeImpostos.realizaCalculo(orcamento, icms);
		calculadorDeImpostos.realizaCalculo(orcamento, iccc);

	}
}
