package br.com.alura.aula4.exercicio6;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.geral.Conta;

class FiltroContasSaldoMenor100 extends Filtro {

	public FiltroContasSaldoMenor100(Filtro outroFiltro) {
		super(outroFiltro);
	}

	public FiltroContasSaldoMenor100() {
	}

	@Override
	public List<Conta> filtra(List<Conta> contas) {
		List<Conta> contasFiltradas = contas.stream()
				.filter(conta -> conta.getSaldo() < 100.0)
				.collect(Collectors.toList());
		contasFiltradas.addAll(aplicarOutroFiltro(contas));
		return contasFiltradas;
	}

}
