package br.com.alura.aula3.exercicio.impostos;

import br.com.alura.aula1.Orcamento;
import br.com.alura.aula3.exemplo.TemplateDeImpostoCondicional;

class ImpostoCondicionalICKV extends TemplateDeImpostoCondicional {

	public boolean deveUsarMaximaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() > 500 && temItemMaiorQue100ReaisNo(orcamento);
	}

	public double maximaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() * 0.10;
	}

	public double minimaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() * 0.06;
	}

	private boolean temItemMaiorQue100ReaisNo(Orcamento orcamento) {
		return (orcamento.getItens()
				.stream()
				.filter(item -> item.getValor() > 100)
				.count() > 0);
	}
}