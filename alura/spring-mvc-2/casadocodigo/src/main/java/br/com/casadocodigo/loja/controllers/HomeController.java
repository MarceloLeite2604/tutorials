package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class HomeController {

	@Autowired
	private ProdutoDAO produtoDAO;

	@RequestMapping("/")
	/*
	 * Para evitar diversos acessos desnecessários no banco de dados, é possível
	 * habilitar a cache para a página home. Para isto, basta utilizar a
	 * annotation "Cache" e dar um nome para a cache elaborada. 
	 */
	@Cacheable(value = "produtosHome")
	public ModelAndView index() {
		System.out.println("Entrando na home da CDC.");

		List<Produto> produtos = produtoDAO.listar();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
}
