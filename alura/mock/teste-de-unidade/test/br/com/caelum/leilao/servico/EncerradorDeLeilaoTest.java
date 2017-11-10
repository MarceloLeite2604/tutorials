package br.com.caelum.leilao.servico;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Matchers;
import org.mockito.Mockito;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.infra.email.Carteiro;

public class EncerradorDeLeilaoTest {

	@Test
	public void deveEncerrarLeiloesQueComecaramUmaSemanaAntes() {
		Calendar antiga = Calendar.getInstance();
		antiga.set(1999, 1, 20);

		Leilao leilao1 = new CriadorDeLeilao().para("TV De Plasma").naData(antiga).constroi();
		Leilao leilao2 = new CriadorDeLeilao().para("Geladeira").naData(antiga).constroi();
		List<Leilao> leiloesAntigos = Arrays.asList(leilao1, leilao2);

		/*
		 * LeilaoDao dao = new LeilaoDao(); LeilaoDaoFalso dao = new LeilaoDaoFalso();
		 * dao.salva(leilao1); dao.salva(leilao2);
		 */

		RepositorioDeLeiloes repositorioDeLeiloesFalso = Mockito.mock(RepositorioDeLeiloes.class);
		Carteiro carteiroFalso = Mockito.mock(Carteiro.class);

		/*
		 * Pede para o mock que, quando o método "correntes" for executado no mock, seja
		 * retornado o array de leilões antigos construídos manualmente.
		 */
		Mockito.when(repositorioDeLeiloesFalso.correntes()).thenReturn(leiloesAntigos);

		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(repositorioDeLeiloesFalso, carteiroFalso);
		encerrador.encerra();

		assertEquals(2, encerrador.getTotalEncerrados());
		assertTrue(leilao1.isEncerrado());
		assertTrue(leilao2.isEncerrado());

	}

	@Test
	public void naoDeveEncerrarLeiloesQueComecaramOntem() {
		Calendar ontem = Calendar.getInstance();
		ontem.add(Calendar.DATE, -1);

		Leilao leilao1 = new CriadorDeLeilao().para("TV De Plasma").naData(ontem).constroi();
		Leilao leilao2 = new CriadorDeLeilao().para("Geladeira").naData(ontem).constroi();
		List<Leilao> leiloesAntigos = new ArrayList<Leilao>();

		/*
		 * LeilaoDao dao = new LeilaoDao(); LeilaoDaoFalso dao = new LeilaoDaoFalso();
		 * dao.salva(leilao1); dao.salva(leilao2);
		 */

		RepositorioDeLeiloes repositorioDeLeiloesFalso = Mockito.mock(RepositorioDeLeiloes.class);
		Carteiro carteiroFalso = Mockito.mock(Carteiro.class);

		Mockito.when(repositorioDeLeiloesFalso.correntes()).thenReturn(leiloesAntigos);

		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(repositorioDeLeiloesFalso, carteiroFalso);
		encerrador.encerra();

		assertEquals(0, encerrador.getTotalEncerrados());
		assertFalse(leilao1.isEncerrado());
		assertFalse(leilao2.isEncerrado());

		Mockito.verify(repositorioDeLeiloesFalso, Mockito.never()).atualiza(leilao1);
	}

	@Test
	public void nenhumLeilaoParaEncerrar() {

		RepositorioDeLeiloes repositorioDeLeiloes = Mockito.mock(RepositorioDeLeiloes.class);
		Mockito.when(repositorioDeLeiloes.correntes()).thenReturn(new ArrayList<Leilao>());

		EncerradorDeLeilao encerrador = new EncerradorDeLeilao(repositorioDeLeiloes, null);
		encerrador.encerra();

		assertEquals(0, encerrador.getTotalEncerrados());
	}

	@Test
	public void testTeste() {
		/* Não é possível mockar funções estáticas. */
		/* Observação: Evite funções estáticas. */
		/* Mockito.when(LeilaoDao.teste()).thenReturn("Olá mundo."); */
	}

	@Test
	public void deveAtualizarLeiloesEncerrados() {
		Calendar antiga = Calendar.getInstance();
		antiga.set(1999, 1, 20);

		Leilao leilao1 = new CriadorDeLeilao().para("TV De Plasma").naData(antiga).constroi();

		RepositorioDeLeiloes repositorioDeLeiloesFalso = Mockito.mock(RepositorioDeLeiloes.class);
		Carteiro carteiroFalso = Mockito.mock(Carteiro.class);

		Mockito.when(repositorioDeLeiloesFalso.correntes()).thenReturn(Arrays.asList(leilao1));

		EncerradorDeLeilao encerradorDeLeilao = new EncerradorDeLeilao(repositorioDeLeiloesFalso, carteiroFalso);
		encerradorDeLeilao.encerra();

		/*
		 * Verifica se um determinado método foi executado dentro do objeto mockado.
		 * Também é possível informar quantas vezes o objeto foi invocado. O verify deve
		 * ser executado após a execução do método analisado.
		 */
		Mockito.verify(repositorioDeLeiloesFalso, Mockito.times(1)).atualiza(leilao1);

		/*
		 * Uma vez criado os mocks, é possível indicar a ordem de execução dos métodos.
		 */
		InOrder inOrder = Mockito.inOrder(repositorioDeLeiloesFalso, carteiroFalso);

		/*
		 * O primeiro a ser executado deve ser o "RepositorioDeLeiloes.atualiza()" uma
		 * vez.
		 */
		inOrder.verify(repositorioDeLeiloesFalso, Mockito.times(1)).atualiza(leilao1);

		/* O segundo a ser executado deve ser o "EnviadorDeEmail.envia()" uma vez. */
		inOrder.verify(carteiroFalso, Mockito.times(1)).envia(leilao1);
	}

	@Test
	public void deveContinuarAExecucaoMesmoQuandoODaoFalha() {
		Calendar antiga = Calendar.getInstance();
		antiga.set(1999, 1, 20);

		Leilao leilao1 = new CriadorDeLeilao().para("TV De Plasma").naData(antiga).constroi();
		Leilao leilao2 = new CriadorDeLeilao().para("Geladeira").naData(antiga).constroi();
		List<Leilao> leiloesAntigos = Arrays.asList(leilao1, leilao2);

		RepositorioDeLeiloes repositorioDeLeiloesFalso = Mockito.mock(RepositorioDeLeiloes.class);
		Carteiro carteiroFalso = Mockito.mock(Carteiro.class);

		Mockito.when(repositorioDeLeiloesFalso.correntes()).thenReturn(leiloesAntigos);

		/*
		 * Caso queira ser simulado um teste de lançamento de exceção, podemos utilizar
		 * o método "doThrow" para solicitar o lançamento deste erro quando o método for
		 * invocado.
		 */
		Mockito.doThrow(new RuntimeException()).when(repositorioDeLeiloesFalso).atualiza(leilao1);

		EncerradorDeLeilao encerradorDeLeilao = new EncerradorDeLeilao(repositorioDeLeiloesFalso, carteiroFalso);
		encerradorDeLeilao.encerra();

		/*
		 * Além disto, uma vez que a exceção foi lançada, temos que verificar que o
		 * e-mail não tenha sido enviado.
		 */
		Mockito.verify(carteiroFalso, Mockito.never()).envia(leilao1);

		Mockito.verify(repositorioDeLeiloesFalso).atualiza(leilao2);
		Mockito.verify(carteiroFalso).envia(leilao2);
	}

	@Test
	public void naoDeveEnviarEmailParaNenhumErroDeDao() {
		Calendar antiga = Calendar.getInstance();
		antiga.set(1999, 1, 20);

		Leilao leilao1 = new CriadorDeLeilao().para("TV De Plasma").naData(antiga).constroi();
		Leilao leilao2 = new CriadorDeLeilao().para("Geladeira").naData(antiga).constroi();
		List<Leilao> leiloesAntigos = Arrays.asList(leilao1, leilao2);

		RepositorioDeLeiloes repositorioDeLeiloesFalso = Mockito.mock(RepositorioDeLeiloes.class);
		Carteiro carteiroFalso = Mockito.mock(Carteiro.class);

		Mockito.when(repositorioDeLeiloesFalso.correntes()).thenReturn(leiloesAntigos);

		/*
		 * Caso queira ser simulado um teste de lançamento de exceção, podemos utilizar
		 * o método "doThrow" para solicitar o lançamento deste erro quando o método for
		 * invocado. A função "Matchers.any" indica que a exceção deve ser lançada
		 * independente do parâmetro recebido.
		 */
		Mockito.doThrow(new RuntimeException()).when(repositorioDeLeiloesFalso).atualiza(Matchers.any(Leilao.class));

		EncerradorDeLeilao encerradorDeLeilao = new EncerradorDeLeilao(repositorioDeLeiloesFalso, carteiroFalso);
		encerradorDeLeilao.encerra();

		/*
		 * Além disto, uma vez que a exceção foi lançada, temos que verificar que o
		 * e-mail não tenha sido enviado. A função "Matchers.any" indica que o método
		 * não deve ser executado nunca independente do parâmetro recebido.
		 */
		Mockito.verify(carteiroFalso, Mockito.never()).envia(Matchers.any(Leilao.class));

		Mockito.verify(repositorioDeLeiloesFalso).atualiza(leilao2);
	}

}
