package br.com.alura.aula4.exercicio5;

import br.com.alura.aula1.Orcamento;

class ImpostoCondicionalICPP extends TemplateDeImpostoCondicional {

	public boolean deveUsarMaximaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() > 500 + calculoDoOutroImposto(orcamento);
	}

	public double maximaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() * 0.07 + calculoDoOutroImposto(orcamento);
	}

	public double minimaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() * 0.05 + calculoDoOutroImposto(orcamento);
	}
}
