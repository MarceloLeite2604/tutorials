package br.com.alura.aula4.exercicio4;

import br.com.alura.aula1.Orcamento;
import br.com.alura.aula4.exemplo.ICMS;
import br.com.alura.aula4.exemplo.Imposto;

public class ExercicioQuatro {

	public static void main(String[] args) {
		Imposto impostoComposto = new ImpostoMuitoAlto(new ICMS());
		
		Orcamento orcamento = new Orcamento(500.0);
		
		System.out.println(impostoComposto.calcula(orcamento));
	}
}
