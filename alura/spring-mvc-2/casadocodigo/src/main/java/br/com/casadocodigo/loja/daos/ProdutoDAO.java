package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Repository
@Transactional
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(Produto produto) {
		manager.persist(produto);
	}

	/*
	 * Quando tentamos abrir uma página que contém uma entidade que possuir seus
	 * detalhes em uma outra tabela, o hibernate acusa um erro dizendo que os
	 * detalhes (preço) do item (produto) não puderam ser carregados. Isto
	 * acontece porque a classe "ProdutoDAO" é transacional, ou seja, só haverá
	 * uma sessão aberta enquanto o método desta classe estiver sendo executado
	 * (o método "listar()", no nosso caso). Com isto, quando mais tarde
	 * solicitamos o preço dos itens, o hibernate não tem mais uma sessão com o
	 * banco de dados para obtê-los. Logo, não é possível obter os preços,
	 * resultando em erro. Uma alternativa para isto é inserir na configuração
	 * do Spring um filtro para indicar ao Spring que uma transação com o banco
	 * deve permanecer aberta até que toda a requisição do usuário tenha sido
	 * atendida.
	 */
	/*
	 * A vantagem desta abordagem é que não é mais necessário cuidar a
	 * inicialização dos detalhes de um item (preços dos produtos). A
	 * desvantagem é que será realizado um número muito maior de queries no
	 * banco de dados.
	 */
	/*public List<Produto> listar() {
		return manager.createQuery("select distinct(p) from Produto p", Produto.class).getResultList();
	}*/

	/*
	 * Por padrão, o hibernate utiliza o lazy fetch para carregar a lsita de
	 * detalhes de um produto. Uma alternativa para evitar isto é utilizar
	 * "join fetch". Ao fazer isto, forçamos o hibernate a carregar junto com os
	 * produtos os seus preços.
	 */
	/*
	 * A vantagem de utilizar o join fetch é que só será necessário uma query no
	 * banco de dados para solucionar a inicialização de todos os itens. A
	 * desvantagem é que será necessário cuidar na hora de escrever a query para
	 * todos os itens que possuírem detalhes. Sempre que for necessário
	 * apresentar os itens com os seus detalhes, a query de aquisição de dados
	 * deverá utilizar o "join fetch".
	 */
	public List<Produto> listar() {
		return manager.createQuery("select distinct p from Produto p join fetch p.precos", Produto.class)
				.getResultList();
	}

	public Produto find(Integer id) {
		/* return manager.find(Produto.class, id); */
		/*
		 * Altera a query para que também sejam obtidos os preços do produto na
		 * query.
		 */
		return manager.createQuery("select distinct(p) from Produto p join fetch p.precos preco where p.id = :id",
				Produto.class).setParameter("id", id).getSingleResult();

	}

	public BigDecimal somaPrecosPorTipoPreco(TipoPreco tipoPreco) {
		TypedQuery<BigDecimal> query = manager.createQuery(
				"select sum(preco.valor) from Produto p join p.precos preco where preco.tipo = :tipoPreco",
				BigDecimal.class);
		query.setParameter("tipoPreco", tipoPreco);
		return query.getSingleResult();
	}
}
