package br.com.alura.aula3.exercicio.relatorios;

import java.util.Arrays;
import java.util.List;

public class TesteDeRelatorio {

	public static void main(String[] args) {
		Banco banco = criarBanco();
		
		System.out.println("Relatório simples:");
		new RelatorioSimples().imprimir(banco);
		System.out.println("\n\nRelatório complexo:");
		new RelatorioComplexo().imprimir(banco);
	}

	private static Banco criarBanco() {
		Conta primeiraConta = new Conta("Marcelo de Moraes Leite", "1", "0001-7", 120.00);
		Conta segundaConta = new Conta("Marcos Vinícios Einsfeldt", "1", "0002-4", 780.12);
		Conta terceiraConta = new Conta("Eduardo Aluísio Fontes", "2", "0098-0", 2365.21);
		List<Conta> contas = Arrays.asList(primeiraConta, segundaConta, terceiraConta);
		return new Banco("Banco Alura", "(+55) 11-9876-5432", "Rua Guajuviras nº 360, Torre D sala 1274",
				"faleconosco@bancoalura.com.br", contas);
	}
}
