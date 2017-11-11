package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDAO;

	@Autowired
	private FileSaver fileSaver;

	@InitBinder
	public void InitBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProdutoValidation());
		new ProdutoValidation();
	}

	@RequestMapping("form")
	public ModelAndView form(Produto produto) {
		/* O parâmetro do objeto é a página a ser visualizada. */
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		/* Repassa para a view objetos do model. */
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}

	/* Método HTTP post é para gravar. */
	/*
	 * Cuidar com a validação dos produtos. Para o Spring, a ordem de
	 * apresentação dos parâmetros é importante, pois logo após o item a ser
	 * validado deve vir o componente que apresenta o resultado da validação.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return form(produto);
		}

		String sumarioPath = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(sumarioPath);

		System.out.println(produto);
		System.out.println(sumario.getOriginalFilename());
		produtoDAO.gravar(produto);
		/* Always redirect after POST. */
		// return listar();
		/*
		 * Flash attribute: Persiste por duas requisições (utilizado junto com o
		 * ARAP)
		 */
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso.");
		return new ModelAndView("redirect:produtos");
	}

	/* Método HTTP get é para listar. */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDAO.listar();

		/* O parâmetro do objeto é a página a ser visualizada. */
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		/* Repassa para a view objetos do model. */
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}

	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		Produto produto = produtoDAO.find(id);
		modelAndView.addObject("produto", produto);
		return modelAndView;
	}
}
