package br.com.casadocodigo.loja.controllers;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.conf.AppWebConfiguration;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.conf.SecurityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
/*
 * Como este teste será realizado com um controller, é necessário adicionar a
 * annotation "WebAppConfiguration" na classe.
 */
@WebAppConfiguration
/*
 * Para que os testes de requisição sejam realizados, é necessário incluir a
 * classe "AppWebConfiguration" na annotation ContextConfiguration.
 */
/*
 * Adicionamos também a classe "SecurityConfiguration" elaborada durante o curso
 * e que contém todas as nossas regras de acesso e segurança.
 */
@ContextConfiguration(classes = { JPAConfiguration.class, AppWebConfiguration.class, DataSourceConfigurationTest.class,
		SecurityConfiguration.class })
@ActiveProfiles("test")
public class ProdutosControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private Filter springSecurityFilterChain;

	@Before
	public void setup() {
		/*
		 * Carrega o mock antes dos testes carregando-o com um contexto defindo
		 * pelo Spring. Este contexto está definido pelo campo
		 * "WebApplicationContext" e a anotação "Autowired" faz com que o Spring
		 * o carregue automaticamente.
		 */
		/*
		 * Adicionamos também um filtro para que o Spring security verifique se
		 * o usuário possui permissão de acesso à página requisitada. A anotação
		 * "Autowired" faz com que o Spring o carregue automaticamente,
		 * utilizando a nossa classe "SecurityConfiguration".
		 */
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(springSecurityFilterChain).build();
	}

	@Test
	public void deveRetornarParaAHome() throws Exception {
		/*
		 * Utilizando o mockMvc, realizamos uma requisição da página "/" para o
		 * Spring e aguardamos que ele nos retorne a página
		 * "/WEB-INF/view/home.jsp".
		 */
		/*
		 * Observação: Estes testes do Spring não são um Mock. Ele está de fato
		 * testando a aplicação.
		 */
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("produtos"))
				.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp"));
	}

	@Test
	public void somenteAdminDeveAcessarProdutosForm() throws Exception {
		/*
		 * Estamos realizando uma requisição de acesso a página "/produtos/form"
		 * como se estivéssemos conectado com o usuário "user@casadocodigo.com",
		 * que só possui a role "USUARIO". Com isto, aguardamos que o controller
		 * nos retorne o status 403 - Forbidden.
		 */
		mockMvc.perform(MockMvcRequestBuilders.get("/produtos/form").with(SecurityMockMvcRequestPostProcessors
				.user("user@casadocodigo.com,br").password("123456").roles("USUARIO")))
				.andExpect(MockMvcResultMatchers.status().is(403));

	}
}
