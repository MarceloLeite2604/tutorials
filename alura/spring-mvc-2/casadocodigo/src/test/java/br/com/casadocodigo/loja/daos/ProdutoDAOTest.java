package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class, ProdutoDAO.class, DataSourceConfigurationTest.class })
/*
 * Dentro do Spring, é possível definir profiles de execução. No exemplo, foram
 * criados dois profiles de execução: test e dev. De acordo com o profile
 * indicado na classe, o sistema irá utilizar os métodos anotados com o mesmo
 * profile de execução.
 */
@ActiveProfiles("test")
public class ProdutoDAOTest {

	@Autowired
	private ProdutoDAO produtoDao = new ProdutoDAO();

	/*
	 * Algo interessante neste teste é que, quando o executamos com o Spring
	 * JUnit, ele se encarrega de limpar os dados do banco após a execução do
	 * teste. Com isto, não é necessário solicitar a exclusão dos dados após a
	 * execução dos testes.
	 */
	@Test
	@Transactional
	public void deveSomarTodosPrecosPorTipoLivro() {

		List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3)
				.buildAll();

		List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).more(3).buildAll();

		livrosImpressos.stream().forEach(produtoDao::gravar);
		livrosEbook.stream().forEach(produtoDao::gravar);

		BigDecimal valor = produtoDao.somaPrecosPorTipoPreco(TipoPreco.EBOOK);

		Assert.assertEquals(new BigDecimal(40).setScale(2), valor);
	}
}
