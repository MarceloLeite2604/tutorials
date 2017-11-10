package br.com.caelum.leilao.servico;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Pagamento;
import br.com.caelum.leilao.infra.dao.RepositorioDePagamentos;

public class GeradorDePagamento {

	private RepositorioDeLeiloes leiloes;
	private Avaliador avaliador;
	private RepositorioDePagamentos repositorioDePagamentos;

	public GeradorDePagamento(RepositorioDeLeiloes leiloes, RepositorioDePagamentos repositorioDePagamentos,
			Avaliador avaliador) {
		super();
		this.leiloes = leiloes;
		this.repositorioDePagamentos = repositorioDePagamentos;
		this.avaliador = avaliador;
	}

	public void gera() {
		List<Leilao> leiloesEncerrados = this.leiloes.encerrados();

		for (Leilao leilao : leiloesEncerrados) {
			this.avaliador.avalia(leilao);

			Pagamento novoPagamento = new Pagamento(avaliador.getMaiorLance(), Calendar.getInstance());
			this.repositorioDePagamentos.salva(novoPagamento);
		}
	}

}
