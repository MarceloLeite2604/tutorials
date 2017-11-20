package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.impl.DefaultCamelContext;

public class AulaQuatro {
	public static void aulaQuatro() throws Exception {
		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				/*
				 * Dependendo da quantidade de transformações e
				 * redirecionamentos que vamos fazer no nosso código, é possível
				 * que o nosso encadeamento de funções fique muito extenso. O
				 * Apache Camel permite que possamos primeiramente definir o
				 * nome de parte das nossas rotas para então elaborá-las. É
				 * importante que o ponto de entrada da nossa rota seja algo
				 * existente. É importante também observar a ordem de inclusão
				 * das rotas.
				 */
				from("file:pedidos?delay=5s&noop=true")
					/*
					 * Para facilitar a compreensão as mensagens de log do
					 * Camel, é possível definir um nome Id para a rota em
					 * questão.
					 */
					.routeId("rota-pedidos")
					/*
					 * A função "multicast" indica que a mesma mensagem será
					 * entregue para as duas subrotas "soap" e "http". Sem a
					 * função "multicast", a mensagem seria primeiro entregue
					 * para a subrota "soap" e então para a subrota "http". Isto
					 * pode ser um problema caso a primeira subrota manipule o
					 * conteúdo da mensagem para então entregar para a segunda
					 * subrota.
					 */
					/*
					 * O multicast não é mais necessário quando substituímos o
					 * tipo da rota de "direct" para "seda".
					 */
					/* .multicast() */
					/*
					 * Utilizando o "parallelProcessing", podemos solicitar ao
					 * Camel que execute o processamento das rotas em paralelo.
					 */
					/*
					 * O parallelProcessing não é mais necessário quando
					 * substituímos o tipo da rota de "direct" para "seda".
					 */
					/* .parallelProcessing() */
					/*
					 * Também podemos definir um timeout de processamento das
					 * rotas.
					 */
					/* .timeout(1000) */
					/*
					 * Ao alterar o tipo da subrota de "direct" para "seda"
					 * (staged event driven architecture), informamos ao Camel
					 * que as rotas serão processadas em paralelo, entregando as
					 * mensagens para filas de consumo da próxima subrota.
					 */
					/* .to("direct:soap") */
					/* .to("direct:http") */
					.to("seda:soap")
					.to("seda:http");

				/* Alteração do tipo da rota de "direct" para "seda". */
				/* from("direct:http") */
				from("seda:http").routeId("rota-http")
					.setProperty("pedidoId", xpath("/pedido/id/text()"))
					.setProperty("clienteId", xpath("/pedido/pagamento/email-titular/text()"))
					.split()
					.xpath("/pedido/itens/item")
					.filter()
					.xpath("/item/formato[text()]='EBOOK'")
					.setProperty("ebookId", xpath("/item/livro/codigo/text()"))
					.marshal()
					.xmljson()
					.log("${id}")
					.setHeader(Exchange.HTTP_METHOD, HttpMethods.GET)
					.setHeader(Exchange.HTTP_QUERY, simple(
							"ebookId=${property.ebookId}&pedidoId=${property.pedidoId}&clienteId=${property.clienteId}"))
					.to("http4://localhost:8080/webservices/ebook/item");

				/* Alteração do tipo da rota de "direct" para "seda". */
				/* from("direct:soap") */
				from("seda:soap").routeId("rota-soap")
					/*
					 * Uma vez que a mensagem tenha sido alterada, ela será
					 * passada desta forma para as demais rotas.
					 */
					.setBody(constant("<envelope>teste</envelope>"))
					/*
					 * Ocasionalmente não teremos definido a rota que deve ser
					 * realizada. Nestes casos podemos utilizar um tipo de rota
					 * chamado "mock", para indicar que a rota ainda não foi
					 * definida.
					 */
					.to("mock:soap");
			}
		});

		context.start();
		Thread.sleep(10000);
		context.stop();
	}
}
