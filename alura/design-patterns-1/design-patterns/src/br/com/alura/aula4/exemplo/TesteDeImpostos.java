package br.com.alura.aula4.exemplo;

import br.com.alura.aula1.Orcamento;

public class TesteDeImpostos {

	public static void main(String[] args) {
		Imposto impostoCompleto = new ISS(new ICMS(new ICPP(new ISS())));
		
		Orcamento orcamento = new Orcamento(500.0);
		
		System.out.println(impostoCompleto.calcula(orcamento));
	}
}
