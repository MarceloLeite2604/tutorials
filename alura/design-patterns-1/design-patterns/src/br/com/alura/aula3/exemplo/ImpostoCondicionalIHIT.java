package br.com.alura.aula3.exemplo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.alura.aula1.Orcamento;
import br.com.alura.aula2.Item;

public class ImpostoCondicionalIHIT extends TemplateDeImpostoCondicional {

	@Override
	public boolean deveUsarMaximaTaxacao(Orcamento orcamento) {
		Set<String> nomeItens = new HashSet<>();
		List<Item> itens = orcamento.getItens();
		itens.forEach(item -> nomeItens.add(item.getNome()));
		return (nomeItens.size() != itens.size());
	}

	@Override
	public double maximaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() * 0.13 + 100.0;
	}

	@Override
	public double minimaTaxacao(Orcamento orcamento) {
		return orcamento.getValor() * (0.01 * orcamento.getItens()
				.size());
	}

}
