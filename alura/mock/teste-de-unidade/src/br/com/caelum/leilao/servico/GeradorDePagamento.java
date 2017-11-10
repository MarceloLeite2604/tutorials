package br.com.caelum.leilao.servico;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Pagamento;
import br.com.caelum.leilao.infra.dao.RepositorioDePagamentos;
import br.com.caelum.leilao.infra.relogio.Relogio;
import br.com.caelum.leilao.infra.relogio.RelogioDoSistema;

public class GeradorDePagamento {

	private RepositorioDeLeiloes leiloes;
	private Avaliador avaliador;
	private RepositorioDePagamentos repositorioDePagamentos;
	private Relogio relogio;

	public GeradorDePagamento(RepositorioDeLeiloes leiloes, RepositorioDePagamentos repositorioDePagamentos,
			Avaliador avaliador, Relogio relogio) {
		super();
		this.leiloes = leiloes;
		this.repositorioDePagamentos = repositorioDePagamentos;
		this.avaliador = avaliador;
		this.relogio = relogio;
	}

	public GeradorDePagamento(RepositorioDeLeiloes leiloes, RepositorioDePagamentos repositorioDePagamentos,
			Avaliador avaliador) {
		this(leiloes, repositorioDePagamentos, avaliador, new RelogioDoSistema());
	}

	public void gera() {
		List<Leilao> leiloesEncerrados = this.leiloes.encerrados();

		for (Leilao leilao : leiloesEncerrados) {
			this.avaliador.avalia(leilao);

			Pagamento novoPagamento = new Pagamento(avaliador.getMaiorLance(), primeiroDiaUtil());
			this.repositorioDePagamentos.salva(novoPagamento);
		}
	}

	private Calendar primeiroDiaUtil() {
		/*
		 * Para evitar a utilização do método estático (e assim poder realizar os
		 * testes) foi criada uma interface que se responsabiliza a obter a data de
		 * hoje.
		 */
		/* Calendar data = Calendar.getInstance(); */
		Calendar data = relogio.hoje();

		int diadaSemana = data.get(Calendar.DAY_OF_WEEK);
		if (diadaSemana == Calendar.SATURDAY) {
			data.add(Calendar.DAY_OF_MONTH, 2);
		}
		if (diadaSemana == Calendar.SUNDAY) {
			data.add(Calendar.DAY_OF_MONTH, 1);
		}
		return data;
	}

}
