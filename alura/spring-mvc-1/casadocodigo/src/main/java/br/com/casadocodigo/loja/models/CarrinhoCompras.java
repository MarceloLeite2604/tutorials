package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/*
 * Quando anotamos uma classe com "Component", por padrão, o Spring cria uma
 * singleton desta classe para trabalhar na aplicação. Isso é equivalente a
 * utilizar a anotação "Scope" com a propriedade "value" com o valor
 * "WebApplicationContext.SCOPE_APPLICATION". Para alterar este comportamento e
 * fazer com que o Spring crie uma instância da classe para cada sessão, é
 * necessário utilizar a anotação "Scope" com a propriedade "value" com o valor
 * "WebApplicationContext.SCOPE_SESSION".
 */
/*
 * Quando um componente possuir o escopo "Session", é importante que a classe
 * implemente o "Serializable", pois quando a aplicação salva a sessão, ele
 * precisa serializar também as suas classes.
 */
/*
 * Ao invés de ter que alterar todos os controllers para ter que informar o
 * escopo "Request", é possível solicitar para o Spring que ele coloque o objeto
 * "CarrinhoCompras" em um proxy, fazendo com que ele mesmo controle a instância
 * do objeto toda vez que ele for informado em um outro objeto com session
 * "Application".
 */
/*
 * De qualquer forma, pessoalmente, ainda acho mais correto informar em cada
 * controller que o seu escopo é de "Request", porque isso de fato está correto!
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompras implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<>();

	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();
	}

	public Integer getQuantidade(CarrinhoItem item) {
		if (!itens.containsKey(item)) {
			itens.put(item, 0);
		}
		return itens.get(item);
	}

	public int getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}

	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item));
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;

		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}

		return total;
	}

	public void remover(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = new Produto();
		produto.setId(produtoId);
		itens.remove(new CarrinhoItem(produto, tipoPreco));
		
	}
}
