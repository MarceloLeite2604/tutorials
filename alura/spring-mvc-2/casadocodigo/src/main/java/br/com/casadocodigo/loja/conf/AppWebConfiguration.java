package br.com.casadocodigo.loja.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.CarrinhoCompras;

@EnableWebMvc
@ComponentScan(basePackageClasses = { HomeController.class, ProdutoDAO.class, FileSaver.class, CarrinhoCompras.class })
/*
 * Para que possamos utilizar a estrutura de cache de páginas, é necessário
 * informar na classe de configuração da web que a cache está habilitada.
 */
@EnableCaching
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
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CacheManager cacheManager() {
		/*
		 * A classe "ConcurrentMapCacheManager" é somente um mapa de chave,
		 * valor fornecido pelo Spring e não deve ser utilizado em produção.
		 * Primeiro porque sua eficiência é fraca e segundo porque ele não
		 * possui muitos mecanismos de configuração.
		 */
		/* return new ConcurrentMapCacheManager(); */

		/*
		 * Primeiro vamos definir as propriedades do nosso cache, indicando que
		 * ele armazenará 100 itens e irá expirar após 5 minutos.
		 */
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5,
				TimeUnit.MINUTES);

		/*
		 * Em seguida iremos instanciar o objeto que irá gerenciar o nosso cache
		 * Guava.
		 */
		GuavaCacheManager guavaCacheManager = new GuavaCacheManager();
		guavaCacheManager.setCacheBuilder(cacheBuilder);

		return guavaCacheManager;
	}

	/*
	 * Para evitar a necessidade de criarmos diversos endereços de acesso ao
	 * nosso sistema quando queremos entregar o mesmo item em vários formatos
	 * (página, Json ou XML), configuramos um view resolver no sistema.
	 */
	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager) {
		List<ViewResolver> viewResolvers = new ArrayList<>();
		viewResolvers.add(internalResourceViewResolver());
		viewResolvers.add(new JsonViewResolver());

		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(viewResolvers);
		resolver.setContentNegotiationManager(manager);

		return resolver;
	}

	/*
	 * Para que o servlet do spring não procure dentro do seu código as
	 * requisições de arquivos de imagem, css e js, é necessário configurar o
	 * handler da servlet para ignorar os pedidos destes tipos de arquivos. Para
	 * isto, é necessário fazer com que a classe de configuração extenda a
	 * classe "WebMvcConfigurerAdapter" e sobrescrever o método
	 * "configureDefaultServletHandling". Dentro desta função, faremos com que o
	 * objeto "DefaultServletHandlerConfigurer" habilite o redirecionamento da
	 * requisição de elementos desconhecidos da servlet (imagens, css e js) para
	 * o servidor de aplicação, pois ele sim sabe como obter estes elementos.
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
