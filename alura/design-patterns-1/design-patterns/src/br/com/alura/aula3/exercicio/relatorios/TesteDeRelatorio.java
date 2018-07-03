package br.com.alura.aula3.exercicio.relatorios;

import java.util.Arrays;
import java.util.List;

import br.com.alura.geral.Banco;
import br.com.alura.geral.Conta;

public class TesteDeRelatorio {

	public static void main(String[] args) {
		Banco banco = criarBanco();

		System.out.println("Relatório simples:");
		new RelatorioSimples().imprimir(banco);
		System.out.println("\n\nRelatório complexo:");
		new RelatorioComplexo().imprimir(banco);
	}

	private static Banco criarBanco() {
		Conta primeiraConta = Conta.builder()
				.titular("Marcelo de Moraes Leite")
				.agencia("1")
				.conta("0001-7")
				.saldo(120.0)
				.build();
		Conta segundaConta = Conta.builder()
				.titular("Marcos Vinícius Einsfeldt")
				.agencia("1")
				.conta("0002-4")
				.saldo(780.12)
				.build();
		Conta terceiraConta = Conta.builder()
				.titular("Eduardo Aluísio Fontes")
				.agencia("2")
				.conta("0098-0")
				.saldo(2365.21)
				.build();
		List<Conta> contas = Arrays.asList(primeiraConta, segundaConta, terceiraConta);
		return Banco.builder()
				.nome("Banco Alura")
				.telefone("(+55) 11-9876-5432")
				.endereco("Rua Guajuviras nº 360, Torre D sala 1274")
				.email("faleconosco@bancoalura.com.br")
				.contas(contas)
				.build();
	}
}
