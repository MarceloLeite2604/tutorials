package br.com.alura.aula4.exercicio6;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.geral.Conta;

class FiltroContasSaldoMaior500Mil extends Filtro {

	public FiltroContasSaldoMaior500Mil(Filtro outroFiltro) {
		super(outroFiltro);
	}

	public FiltroContasSaldoMaior500Mil() {
	}

	@Override
	public List<Conta> filtra(List<Conta> contas) {
		List<Conta> contasFiltradas = contas.stream()
				.filter(conta -> conta.getSaldo() > 500000.0)
				.collect(Collectors.toList());
		contasFiltradas.addAll(aplicarOutroFiltro(contas));
		return contasFiltradas;
	}

}
