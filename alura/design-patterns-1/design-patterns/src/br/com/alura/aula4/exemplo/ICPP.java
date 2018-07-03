package br.com.alura.aula4.exemplo;

import br.com.alura.aula1.Orcamento;

class ICPP extends Imposto {
	
	public ICPP(Imposto outroImposto) {
		super(outroImposto);
	}
	
	public ICPP() {
	}
	
	public double calcula(Orcamento orcamento) {
		if (orcamento.getValor() > 500) {
			return orcamento.getValor() * 0.07 + calculoDoOutroImposto(orcamento);
		} else {
			return orcamento.getValor() * 0.05 + calculoDoOutroImposto(orcamento);
		}
	}
}
