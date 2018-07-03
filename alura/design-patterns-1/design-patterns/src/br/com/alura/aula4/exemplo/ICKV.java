package br.com.alura.aula4.exemplo;

import br.com.alura.aula1.Orcamento;
import br.com.alura.aula2.Item;

class IKCV extends Imposto {
	
	public IKCV(Imposto outroImposto) {
		super(outroImposto);
	}
	
	public IKCV() {
	}
	
	public double calcula(Orcamento orcamento) {
		if (orcamento.getValor() > 500 && temItemMaiorQue100ReaisNo(orcamento)) {
			return orcamento.getValor() * 0.10 + calculoDoOutroImposto(orcamento);
		} else {
			return orcamento.getValor() * 0.06 + calculoDoOutroImposto(orcamento);
		}
	}

	private boolean temItemMaiorQue100ReaisNo(Orcamento orcamento) {
		for (Item item : orcamento.getItens()) {
			if (item.getValor() > 100)
				return true;
		}
		return false;
	}
}
