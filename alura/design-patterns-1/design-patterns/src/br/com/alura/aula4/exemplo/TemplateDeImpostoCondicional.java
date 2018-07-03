package br.com.alura.aula4.exemplo;

import br.com.alura.aula1.Imposto;
import br.com.alura.aula1.Orcamento;

public abstract class TemplateDeImpostoCondicional implements Imposto {

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
