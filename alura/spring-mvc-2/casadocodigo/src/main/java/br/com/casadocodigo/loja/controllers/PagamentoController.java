package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;
import br.com.casadocodigo.loja.models.Usuario;

@RequestMapping("/pagamento")
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MailSender sender;

	/*
	 * Esta é a implementação original do método "finalizar". Entretanto, da
	 * forma como está implementado, a função faz com que o servidor permaneça
	 * aguardando a resposta da requisição REST realizada. Com isto, o servidor
	 * de aplicação permanece aguardando a resposta do serviço, consumindo tempo
	 * de processamento.
	 */
	@RequestMapping(value = "/oldFinalizar", method = RequestMethod.POST)
	public ModelAndView oldFinalizar(RedirectAttributes model) {
		System.out.println(carrinho.getTotal());

		String uri = "http://book-payment.herokuapp.com/payment";

		try {
			String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
			/*
			 * model.addFlashAttribute("sucesso",
			 * "Pagamento realizado com sucesso.");
			 */
			System.out.println(response);
			model.addFlashAttribute("sucesso", response);
			return new ModelAndView("redirect:/produtos");
		} catch (HttpClientErrorException httpClientErrorException) {
			httpClientErrorException.printStackTrace();
			model.addFlashAttribute("falha", "Valor maior que o permitido.");
			return new ModelAndView("redirect:/produtos");
		}
	}

	/*
	 * Retornando um objeto "Callable", o servidor compreende que deve realizar
	 * a execução assíncrona, disponibilizando o processador para as requisições
	 * de outros usuários. No exemplo abaixo, é utilizada a definição de uma
	 * classe anônima para montar um objeto "Callable" com todo o conteúdo
	 * escrito no método "finalizar original".
	 */
	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes model) {
		return () -> {
			System.out.println(carrinho.getTotal());

			String uri = "http://book-payment.herokuapp.com/payment";
			
			try {
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()),
						String.class);
				/*
				 * model.addFlashAttribute("sucesso",
				 * "Pagamento realizado com sucesso.");
				 */
				System.out.println(response);

				enviaEmailDeCompraProduto(usuario);

				model.addFlashAttribute("sucesso", response);
				return new ModelAndView("redirect:/produtos");
			} catch (HttpClientErrorException httpClientErrorException) {
				httpClientErrorException.printStackTrace();
				model.addFlashAttribute("falha", "Valor maior que o permitido.");
				return new ModelAndView("redirect:/produtos");
			}
		};
	}

	private void enviaEmailDeCompraProduto(Usuario usuario) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Compra finalizada com sucesso.");
		/*email.setTo(usuario.getEmail());*/
		email.setTo("marceloleite2604@gmail.com");
		email.setText("Compra aprovada com sucesso no valor de " + carrinho.getTotal());
		email.setFrom("compras@casadocodigo.com.br");
		
		sender.send(email);
	}
}
