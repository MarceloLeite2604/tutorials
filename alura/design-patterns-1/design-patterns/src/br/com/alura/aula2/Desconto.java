package br.com.alura.aula2;

import br.com.alura.aula1.Orcamento;

public interface Desconto {

	double desconta(Orcamento orcamento);
	void setProximo(Desconto desconto);
}
