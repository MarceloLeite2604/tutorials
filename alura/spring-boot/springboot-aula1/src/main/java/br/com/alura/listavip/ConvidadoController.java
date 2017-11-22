package br.com.alura.listavip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.listavip.model.Convidado;
import br.com.alura.listavip.repository.ConvidadoRepository;

/*
 * Ver o curso de spring MVC.
 */
@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoRepository repository;

	@RequestMapping("/")
	public String index() {
		/* Não é necessário inserir o sufixo ".html". */
		return "index";
	}

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
			@RequestParam("telefone") String telefone, Model model) {
		Convidado convidado = new Convidado(nome, email, telefone);
		repository.save(convidado);
		
		Iterable<Convidado> convidados = repository.findAll();
		model.addAttribute("convidados", convidados);
		return "listaconvidados";
	}

	@RequestMapping("listaconvidados")
	public String listaConvidados(Model model) {
		Iterable<Convidado> convidados = repository.findAll();
		/*
		 * Encaminha o contéudo para a view.
		 */
		model.addAttribute("convidados", convidados);
		return "listaconvidados";
	}

}
