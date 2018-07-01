package br.com.alura.aula3.exercicio.impostos;

import br.com.alura.aula1.Orcamento;
import br.com.alura.aula3.exemplo.TemplateDeImpostoCondicional;

class ImpostoCondicionalICPP extends TemplateDeImpostoCondicional {

	public boolean deveUsarMaximaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() > 500;
	}

	public double maximaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() * 0.07;
	}

	public double minimaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() * 0.05;
	}
}
