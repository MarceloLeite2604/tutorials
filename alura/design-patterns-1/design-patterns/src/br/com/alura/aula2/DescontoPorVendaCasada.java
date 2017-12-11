package br.com.alura.aula2;

import br.com.alura.aula1.Orcamento;

public class DescontoPorVendaCasada implements Desconto {

	private Desconto desconto;

	@Override
	public double desconta(Orcamento orcamento) {
		if (existe("Lápis", orcamento) && existe("Caneta", orcamento)) {
			return orcamento.getValor() * 0.05;
		} else {
			return desconto.desconta(orcamento);
		}
	}

	@Override
	public void setProximo(Desconto desconto) {
		this.desconto = desconto;

	}

	private boolean existe(String nomeDoItem, Orcamento orcamento) {
		for (Item item : orcamento.getItens()) {
			if (item.getNome().equals(nomeDoItem))
				return true;
		}
		return false;
	}
}
