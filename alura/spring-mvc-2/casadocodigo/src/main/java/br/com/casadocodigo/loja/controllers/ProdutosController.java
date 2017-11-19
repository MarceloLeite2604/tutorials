package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	/*
	 * Para que o sistema invalide da cache elaborada para a página home, é
	 * utilizada a annotation "CacheEvict" toda vez que a função "gravar" for
	 * executada. Ainda, a invalidação é para todos os registros da cache
	 * (allEntries).
	 */
	@CacheEvict(value = "produtosHome", allEntries = true)
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

	/*
	 * Existem duas formas de obter o Json do nosso produto. A primeira é
	 * colocar no header da requisição HTML a propriedade "Accept" com o valor
	 * "application/json". A segunda forma é colocar no final do endereço o
	 * sufixo ".json".
	 */
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		Produto produto = produtoDAO.find(id);
		modelAndView.addObject("produto", produto);

		/*if (true) {
			throw new RuntimeException("Exceção genérica aconteçendo.");
		}*/
		return modelAndView;
	}

	/*
	 * Quando queremos que outros sistemas se integrem com os nossos dados, é
	 * interessante elaborar um serviço que retorne os dados em um formato
	 * conhecido como por exemplo XML ou Json. Para fazer isto, basta retornar o
	 * próprio elemento que se deseja transmitir e colocar na função do
	 * controller a annotation "ResponseBody". Esta annotation retornará no
	 * corpo do HTML o que vier do objeto retornado pela função.
	 */
	/*
	 * Observação: Para que o retorno no formato Json funcione, é necessário
	 * incluir no projeto a biblioteca Jackson, que é responsável por realizar
	 * esta transcrição de dados no formato Json.
	 */
	/*
	 * Neste caso a função será comentada porque queremos que os dados em
	 * formato Json do produto sejam distribuídos através da página
	 * "/produtos/detalhe/{id}.json", que é o mesmo endereço da página HTML,
	 * porém com o sufixo Json. Para isso será utilizado uma ViewResolver
	 * configurada na classe AppWebConfiguration.
	 */
	@RequestMapping("/{id}")
	@ResponseBody
	public Produto detalheJson(@PathVariable("id") Integer id) {
		return produtoDAO.find(id);
	}

	/*
	 * Utilizando a annotation "ExceptionHandler", podemos informar para o
	 * spring o que deve ser feito no momento que ocorrer uma determinada
	 * exceção no nosso sistema. Neste caso, estamos redirecionando o usuário
	 * para a página "error.jsp"
	 */
	/*
	 * Observe que este tratamento só será realizando para o ProdutosController.
	 * Se ocorrer um erro em outro controller (por exemplo,
	 * PagamentoController), o Spring não irá utilizar este método.
	 */
	/* @ExceptionHandler(NoResultException.class) */
	/*
	 * Se quisermos que o método capture qualquer erro ocorrido, podemos colocar
	 * a annotation "ExceptionHandler" informando a classe Exception.
	 */
	/*@ExceptionHandler(Exception.class)
	public String trataDetalheNaoEncontrado() {
		return "error";
	}*/
}
