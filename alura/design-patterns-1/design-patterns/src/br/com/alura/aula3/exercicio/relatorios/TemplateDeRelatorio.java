package br.com.alura.aula3.exercicio.relatorios;

public abstract class TemplateDeRelatorio implements Relatorio {
	
	public void imprimir(Banco banco) {
		cabecalho(banco);
		corpo(banco);
		rodape(banco);
	}

	protected abstract void cabecalho(Banco banco);
	
	protected abstract void corpo(Banco banco);
	
	protected abstract void rodape(Banco banco);
}
