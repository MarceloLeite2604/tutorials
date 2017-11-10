package br.com.caelum.leilao.servico;

import java.util.Arrays;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Pagamento;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.infra.dao.RepositorioDePagamentos;
import br.com.caelum.leilao.infra.relogio.Relogio;

public class GeradorDePagamentoTest {

	@Test
	public void deveGerarPagamentoParaUmLeilaoEncerrado() {
		RepositorioDeLeiloes repositorioDeLeiloes = Mockito.mock(RepositorioDeLeiloes.class);
		RepositorioDePagamentos repositorioDePagamentos = Mockito.mock(RepositorioDePagamentos.class);
		Avaliador avaliador = new Avaliador();

		Leilao leilao = new CriadorDeLeilao().para("Playstation").lance(new Usuario("José da Silva"), 2000.0)
				.lance(new Usuario("Maria Pereira"), 2500.0).constroi();

		Mockito.when(repositorioDeLeiloes.encerrados()).thenReturn(Arrays.asList(leilao));

		GeradorDePagamento geradorDePagamento = new GeradorDePagamento(repositorioDeLeiloes, repositorioDePagamentos,
				avaliador);
		geradorDePagamento.gera();

		/*
		 * Como testar o pagamento que foi gerado? Ele cria o objeto dentro da função,
		 * mas não temos acesso externo a ela.
		 */
		/*
		 * Como, dentro do código em análise, o pagamento foi enviado para o objeto mock
		 * "repositorioDePagamentos", podemos obtê-lo a partir daí.
		 */
		/*
		 * O Mockito possui uma classe chamada "ArgumentCaptor" que é capaz de realizar
		 * a captura do argumento informado no momento que uma função é executada.
		 */
		ArgumentCaptor<Pagamento> argumentoPagamento = ArgumentCaptor.forClass(Pagamento.class);

		/*
		 * Para capturar o argumento, na construção do "verify", é necessário colocá-lo
		 * como argumento dentro da função onde ocorrerá a captura.
		 */
		Mockito.verify(repositorioDePagamentos).salva(argumentoPagamento.capture());
		Pagamento pagamentoGerado = argumentoPagamento.getValue();

		Assert.assertEquals(2500.0, pagamentoGerado.getValor(), 0);
	}

	@Test
	public void deveEmpurrarParaOProximoDiaUtil() {
		RepositorioDeLeiloes repositorioDeLeiloes = Mockito.mock(RepositorioDeLeiloes.class);
		RepositorioDePagamentos repositorioDePagamentos = Mockito.mock(RepositorioDePagamentos.class);
		Relogio relogio = Mockito.mock(Relogio.class);

		/*
		 * Esta data será retornada pelo mock "Relogio" quando a data de hoje for
		 * solicitada.
		 */
		/*Calendar sabado = Calendar.getInstance();
		sabado.set(2012, Calendar.APRIL, 7);*/
		Calendar domingo = Calendar.getInstance();
		domingo.set(2012, Calendar.APRIL, 8);

		Leilao leilao = new CriadorDeLeilao().para("Playstation").lance(new Usuario("José da Silva"), 2000.0)
				.lance(new Usuario("Maria Pereira"), 2500.0).constroi();

		Mockito.when(repositorioDeLeiloes.encerrados()).thenReturn(Arrays.asList(leilao));
		Mockito.when(relogio.hoje()).thenReturn(domingo);

		GeradorDePagamento geradorDePagamento = new GeradorDePagamento(repositorioDeLeiloes, repositorioDePagamentos,
				new Avaliador(), relogio);

		geradorDePagamento.gera();

		ArgumentCaptor<Pagamento> argumentoPagamento = ArgumentCaptor.forClass(Pagamento.class);
		Mockito.verify(repositorioDePagamentos).salva(argumentoPagamento.capture());

		Pagamento pagamentoGerado = argumentoPagamento.getValue();

		/*
		 * No caso deste teste, queremos verificar se, quando o pagamento for gerado num
		 * final de semana, ele seja empurrado para o próximo dia últil (Segunda), mas
		 * como fazer isto se, no método "GeradorDePagamento.primeiroDiaUtil" a data
		 * atual é obtida através de um método estático de "Calendar"?
		 */
		/*
		 * Classes estáticas são difíceis de testar. Neste caso a solução é criar uma
		 * interface que fará a vez de obter a data atual. Esta interface será
		 * instanciada no teste como um mock.
		 */
		Assert.assertEquals(Calendar.MONDAY, pagamentoGerado.getData().get(Calendar.DAY_OF_WEEK), 0);
		Assert.assertEquals(9, pagamentoGerado.getData().get(Calendar.DAY_OF_MONTH), 0);
	}
}
