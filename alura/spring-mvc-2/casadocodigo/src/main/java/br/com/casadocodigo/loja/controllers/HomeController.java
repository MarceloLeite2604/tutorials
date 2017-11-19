package br.com.casadocodigo.loja.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.daos.UsuarioDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class HomeController {

	@Autowired
	private ProdutoDAO produtoDAO;
	private UsuarioDAO usuarioDao;

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
	
	@Transactional
	@ResponseBody
	@RequestMapping("/url-magica-maluca-vldofw309ifgreiojr09gu0i9wejpfj0wijf00e")
	public String urlMagicaMaluca() {
		Usuario usuario = new Usuario();
		
		usuario.setNome("Admin");
		usuario.setEmail("admin@casadocodigo.com.br");
		usuario.setSenha("$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu");
		usuario.setRoles(Arrays.asList(new Role("ADMIN")));
		usuarioDao.gravar(usuario);
		return "Url mágica executada.";
	}
}
