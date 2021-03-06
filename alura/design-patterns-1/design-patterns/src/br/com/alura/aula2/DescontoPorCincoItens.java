package br.com.alura.aula2;

import br.com.alura.aula1.Orcamento;

public class DescontoPorCincoItens implements Desconto {

	private Desconto desconto;

	public double desconta(Orcamento orcamento) {
		if (orcamento.getItens().size() > 5) {
			return orcamento.getValor() * 0.1;
		} else {
			return desconto.desconta(orcamento);
		}
	}

	@Override
	public void setProximo(Desconto desconto) {
		this.desconto = desconto;

	}
}
