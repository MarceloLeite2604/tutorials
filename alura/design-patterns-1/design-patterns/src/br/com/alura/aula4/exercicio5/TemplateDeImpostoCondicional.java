package br.com.alura.aula4.exercicio5;

import br.com.alura.aula1.Orcamento;
import br.com.alura.aula4.exemplo.Imposto;

public abstract class TemplateDeImpostoCondicional extends Imposto {
	
	public TemplateDeImpostoCondicional(Imposto outroImposto) {
		super(outroImposto);
	}
	
	public TemplateDeImpostoCondicional() {
	}

	public double calcula(Orcamento orcamento) {

		if (deveUsarMaximaTaxacao(orcamento)) {
			return maximaTaxacao(orcamento);
		} else {
			return minimaTaxacao(orcamento);
		}
	}

	public abstract boolean deveUsarMaximaTaxacao(Orcamento orcamento);

	public abstract double maximaTaxacao(Orcamento orcamento);

	public abstract double minimaTaxacao(Orcamento orcamento);
}
