package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*
	 * Classes carregadas no momento que a aplicação for inicializada no
	 * servidor. No caso colocamos a classe responsável pela segurança da
	 * aplicação.
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SecurityConfiguration.class, AppWebConfiguration.class, JPAConfiguration.class,
				JPAProductionConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		/*
		 * A utilização inclusão do OpenEntitiyManagerInViewFilter faz com que o
		 * Spring mantenha uma sessão aberta no banco de dados até a conclusão
		 * da requisição do usuário. Isto é interessante quando queremos obter o
		 * conteúdo de várias tabelas para apresentar em uma página (por
		 * exemplo, a lista de produtos e a lista de tipo de preços para cada
		 * produto).
		 */
		return new Filter[] { characterEncodingFilter, new OpenEntityManagerInViewFilter() };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
		super.customizeRegistration(registration);
	}

	/*@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		
		 * Adicionamos um listener para escutar as requisições de contexto do
		 * Spring e, em seguida, definimos um parâmetro que indica o profile que
		 * estará ativo na sua execução (no caso "dev).
		 
		
		 * Atualmente temos dois profiles de execução na nossa aplicação:
		 * "teste" para testes com a aplicação e "dev" para o ambiente de
		 * desenvolvimento.
		 
		servletContext.addListener(RequestContextListener.class);
		servletContext.setInitParameter("spring.profiles.active", "dev");
	}*/
}
