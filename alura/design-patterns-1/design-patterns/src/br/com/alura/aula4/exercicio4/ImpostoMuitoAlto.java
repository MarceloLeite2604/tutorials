package br.com.alura.aula4.exercicio4;

import br.com.alura.aula1.Orcamento;
import br.com.alura.aula4.exemplo.Imposto;

public class ImpostoMuitoAlto extends Imposto {
	
	public ImpostoMuitoAlto(Imposto outroImposto) {
		super(outroImposto);
	}

	@Override
	public double calcula(Orcamento orcamento) {
		return orcamento.getValor() * 0.2 + calculoDoOutroImposto(orcamento);
	}

}
