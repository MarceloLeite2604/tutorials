package br.com.alura.aula3.exemplo;

import br.com.alura.aula1.Imposto;
import br.com.alura.aula1.Orcamento;
import br.com.alura.aula2.Item;

class IKCV implements Imposto {
	public double calcula(Orcamento orcamento) {
		if (orcamento.getValor() > 500 && temItemMaiorQue100ReaisNo(orcamento)) {
			return orcamento.getValor() * 0.10;
		} else {
			return orcamento.getValor() * 0.06;
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
