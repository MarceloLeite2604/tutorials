package br.com.alura.aula4.exemplo;

import br.com.alura.aula1.Orcamento;

public class ICMS extends Imposto {
	
	public ICMS(Imposto outroImposto) {
		super(outroImposto);
	}
	
	public ICMS() {
	}
	
	@Override
	public double calcula(Orcamento orcamento) {
		return orcamento.getValor() * 0.1 + calculoDoOutroImposto(orcamento);
	}
}
