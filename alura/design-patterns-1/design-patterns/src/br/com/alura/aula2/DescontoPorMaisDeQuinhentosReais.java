package br.com.alura.aula2;

import br.com.alura.aula1.Orcamento;

public class DescontoPorMaisDeQuinhentosReais implements Desconto {

	private Desconto desconto;

	public double desconta(Orcamento orcamento) {
		if (orcamento.getValor() > 500.0) {
			return orcamento.getValor() * 0.07;
		} else {
			return desconto.desconta(orcamento);
		}
	}

	@Override
	public void setProximo(Desconto desconto) {
		this.desconto = desconto;

	}
}
