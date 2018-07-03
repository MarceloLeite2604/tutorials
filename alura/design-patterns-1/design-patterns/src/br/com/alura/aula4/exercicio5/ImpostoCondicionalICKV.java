package br.com.alura.aula4.exercicio5;

import br.com.alura.aula1.Orcamento;
import br.com.alura.aula4.exemplo.Imposto;

class ImpostoCondicionalICKV extends TemplateDeImpostoCondicional {

	public ImpostoCondicionalICKV(Imposto outroImposto) {
		super(outroImposto);
	}
	
	public ImpostoCondicionalICKV() {
	}

	public boolean deveUsarMaximaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() > 500 && temItemMaiorQue100ReaisNo(orcamento);
	}

	public double maximaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() * 0.10 + calculoDoOutroImposto(orcamento);
	}

	public double minimaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() * 0.06 + calculoDoOutroImposto(orcamento);
	}

	private boolean temItemMaiorQue100ReaisNo(Orcamento orcamento) {
		return (orcamento.getItens()
				.stream()
				.filter(item -> item.getValor() > 100)
				.count() > 0);
	}
}