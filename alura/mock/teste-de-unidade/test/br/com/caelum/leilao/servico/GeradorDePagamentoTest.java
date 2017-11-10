package br.com.caelum.leilao.servico;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Pagamento;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.infra.dao.RepositorioDePagamentos;

public class GeradorDePagamentoTest {

	@Test
	public void deveGerarPagamentoParaUmLeilaoEncerrado() {
		RepositorioDeLeiloes repositorioDeLeiloes = Mockito.mock(RepositorioDeLeiloes.class);
		RepositorioDePagamentos repositorioDePagamentos = Mockito.mock(RepositorioDePagamentos.class);
		Avaliador avaliador = Mockito.mock(Avaliador.class);

		Leilao leilao = new CriadorDeLeilao().para("Playstation").lance(new Usuario("José da Silva"), 2000.0)
				.lance(new Usuario("Maria Pereira"), 2500.0).constroi();

		Mockito.when(repositorioDeLeiloes.encerrados()).thenReturn(Arrays.asList(leilao));
		Mockito.when(avaliador.getMaiorLance()).thenReturn(2500.0);

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
}
