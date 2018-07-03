package br.com.alura.aula4.exercicio6;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.geral.Conta;

class FiltroContasAbertaMesAtual extends Filtro {

	public FiltroContasAbertaMesAtual(Filtro outroFiltro) {
		super(outroFiltro);
	}

	public FiltroContasAbertaMesAtual() {
	}

	@Override
	public List<Conta> filtra(List<Conta> contas) {
		List<Conta> contasFiltradas = contas.stream()
				.filter(conta -> conta.getDataAbertura()
						.getMonth()
						.equals(LocalDateTime.now()
								.getMonth()))
				.collect(Collectors.toList());
		contasFiltradas.addAll(aplicarOutroFiltro(contas));
		return contasFiltradas;
	}
}
