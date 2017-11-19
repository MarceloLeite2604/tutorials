package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

/**
 * Quando um dos componentes "autowired" do controller for do tipo
 * "SCOPE_SESSION" (ver a classe "CarrinhoCompras") o Spring irá lançar uma
 * exceção informando que a classe que possui o item "autowired" e que é do
 * escopo "SCOPE_APPLICATION" não pode persistir por toda a sessão. Uma
 * recomendação é que, para as classes "controllers", o escopo de existência
 * seja alterado para "SCOPE_REQUEST". Desta forma, a instância só será criada
 * quando o usuário realizar uma requisição e, uma vez que esta requisição for
 * concluída, a instância será encerrada.
 */
@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDAO produtoDAO;

	@Autowired
	private CarrinhoCompras carrinho;

	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);

		carrinho.add(carrinhoItem);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView itens() {
		/*
		 * if (true) { throw new
		 * RuntimeException("Exceção genérica aconteçendo."); }
		 */
		return new ModelAndView("carrinho/itens");
	}

	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDAO.find(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
		return carrinhoItem;
	}

	@RequestMapping("/remover")
	public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco) {
		carrinho.remover(produtoId, tipoPreco);

		return new ModelAndView("redirect:/carrinho");
	}
}
