package br.com.alura.listavip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.enviadoremail.EmailService;
import br.com.alura.listavip.model.Convidado;
import br.com.alura.listavip.service.ConvidadoService;

/*
 * Ver o curso de spring MVC.
 */
@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoService service = new ConvidadoService();

	@RequestMapping("/")
	public String index() {
		/* Não é necessário inserir o sufixo ".html". */
		return "index";
	}

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
			@RequestParam("telefone") String telefone, Model model) {
		Convidado convidado = new Convidado(nome, email, telefone);
		service.salvar(convidado);

		new EmailService().enviar(nome, email);

		/*
		 * Não é uma boa prática colocar regras de negócio na camada de
		 * controle. O correto é colocar na camada de serviço.
		 */
		Iterable<Convidado> convidados = service.obterTodos();
		model.addAttribute("convidados", convidados);
		return "listaconvidados";
	}

	@RequestMapping("listaconvidados")
	public String listaConvidados(Model model) {
		Iterable<Convidado> convidados = service.obterTodos();
		/*
		 * Encaminha o conteúdo para a view.
		 */
		model.addAttribute("convidados", convidados);
		return "listaconvidados";
	}

}
