package br.com.alura.aula3.exercicio.relatorios;

public class RelatorioSimples extends TemplateDeRelatorio {

	@Override
	protected void cabecalho(Banco banco) {
		System.out.println(banco.getNome());
	}

	@Override
	protected void corpo(Banco banco) {
		for (Conta conta : banco.getContas()) {
			System.out.println("\t" + conta.getTitular() + ": R$ " + conta.getSaldo());
		}

	}

	@Override
	protected void rodape(Banco banco) {
		System.out.println("Telefone: " + banco.getTelefone());
	}

}
