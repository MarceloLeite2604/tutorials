package br.com.alura.aula4.exercicio6;

import java.util.List;

import br.com.alura.geral.Conta;

abstract class Filtro {

	protected Filtro outroFiltro;

	public Filtro(Filtro outroFiltro) {
		this.outroFiltro = outroFiltro;
	}

	public Filtro() {
		this.outroFiltro = null;
	}

	public abstract List<Conta> filtra(List<Conta> contas);

	protected List<Conta> aplicarOutroFiltro(List<Conta> contas) {
		return outroFiltro.aplicarOutroFiltro(contas);
	}
}
