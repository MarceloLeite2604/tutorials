package br.com.alura.aula1;

public class CalculadorDeImpostos {

	public void realizaCalculo(Orcamento orcamento, Imposto impostoQualquer) {
		double valor = impostoQualquer.calcula(orcamento);
		System.out.println(valor);
	}
}
