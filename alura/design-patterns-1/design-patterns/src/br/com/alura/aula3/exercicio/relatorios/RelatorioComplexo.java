package br.com.alura.aula3.exercicio.relatorios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RelatorioComplexo extends TemplateDeRelatorio {

	@Override
	protected void cabecalho(Banco banco) {
		System.out.println(banco.getNome() + " - " + banco.getEndereco() + ", telefone: " + banco.getTelefone());
	}

	@Override
	protected void corpo(Banco banco) {
		for (Conta conta : banco.getContas()) {
			System.out.println("\t" + conta.getTitular() + " [" + conta.getAgencia() + " " + conta.getConta() + "]: R$ "
					+ conta.getSaldo());
		}

	}

	@Override
	protected void rodape(Banco banco) {
		System.out.println("E-mail: " + banco.getEmail() + " Data: " + obterDataAtualFormatada());
	}

	private String obterDataAtualFormatada() {
		return DateTimeFormatter.ofPattern("dd/MM/yyyy")
				.format(LocalDateTime.now());
	}

}
