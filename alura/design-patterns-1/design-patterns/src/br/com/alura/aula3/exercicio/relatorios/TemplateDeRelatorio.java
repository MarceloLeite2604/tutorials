package br.com.alura.aula3.exercicio.relatorios;

import br.com.alura.geral.Banco;

public abstract class TemplateDeRelatorio implements Relatorio {
	
	public final void imprimir(Banco banco) {
		cabecalho(banco);
		corpo(banco);
		rodape(banco);
	}

	protected abstract void cabecalho(Banco banco);
	
	protected abstract void corpo(Banco banco);
	
	protected abstract void rodape(Banco banco);
}
