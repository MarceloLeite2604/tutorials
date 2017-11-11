package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.CarrinhoCompras;

@EnableWebMvc
@ComponentScan(basePackageClasses = { HomeController.class, ProdutoDAO.class, FileSaver.class, CarrinhoCompras.class })
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");

		/*
		 * Todos os beans ficarão disponíveis como atributos na JSP. É perigoso,
		 * pois todos os beans controlados pelos Spring estão sendo expostos.
		 */
		/* resolver.setExposeContextBeansAsAttributes(true); */

		/*
		 * Uma alternativa para o "setExposeContextBeansAttributes" é o
		 * "setExposedContextBeanNames", onde somente os beans informados é que
		 * serão expostos para o JSP.
		 */
		resolver.setExposedContextBeanNames("carrinhoCompras");

		return resolver;

	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);
		return messageSource;
	}

	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService defaultFormattingConversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar dateFormatterRegistrar = new DateFormatterRegistrar();
		dateFormatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		dateFormatterRegistrar.registerFormatters(defaultFormattingConversionService);

		return defaultFormattingConversionService;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations(
                "/resources/");
    }
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
