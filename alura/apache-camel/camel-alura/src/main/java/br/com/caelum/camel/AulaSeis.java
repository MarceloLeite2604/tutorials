package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.impl.DefaultCamelContext;

public class AulaSeis {
	public static void aulaSeis() throws Exception {
		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				/*
				 * A definição do error handler deve ser a primeira coisa feita
				 * no Camel. Se a definição for realizada depois, o Camel
				 * apresentará um erro.
				 */
				/*
				 * O deadLetterChannel pode ser considerado uma rota para as
				 * mensagens que contém erros.
				 */
				errorHandler(deadLetterChannel("file:erro")
					/*
					 * O logExhaustedMessageHistory solicita que seja
					 * apresentada as mensagens de erro no log.
					 */
					.logExhaustedMessageHistory(true)
					/*
					 * Caso tenhamos diversas rotas de processamento de mensagem
					 * e, por algum motivo, alguma delas resultou em erro,
					 * podemos solicitar ao error handler que armazene não a
					 * mensagem que já foi manipulada, mas sim a mensagem
					 * original.
					 */
					.useOriginalMessage()
					/*
					 * Caso ocorra um erro na entrega da mensagem, solicita até
					 * três vezes uma nova entrega.
					 */
					.maximumRedeliveries(3)
					/*
					 * Aguarda dois segundos para tentar reenviar a mensagem.
					 */
					.maximumRedeliveryDelay(2000)
					/*
					 * Podemos definir um processo executado para quando o
					 * sistema tentar realizar uma reentrega de mensagem.
					 */
					.onRedelivery(new Processor() {

						@Override
						public void process(Exchange exchange) throws Exception {
							/*
							 * É possível extrair informações de entrega da
							 * mensagem através do objeto Exchange recebido no
							 * parâmetro.
							 */
							int counter = (int) exchange.getIn()
								.getHeader(Exchange.REDELIVERY_COUNTER);
							int max = (int) exchange.getIn()
								.getHeader(Exchange.REDELIVERY_MAX_COUNTER);
							System.out.println("Redelivery " + counter + "/" + max);
						}
					}));
				
				from("file:pedidos?delay=5s&noop=true").routeId("rota-pedidos")
					.to("validator:pedido.xsd");
				/*
				 * .multicast() .parallelProcessing() .timeout(1000)
				 * .to("direct:soap") .to("direct:http");
				 */

				from("direct:http").routeId("rota-http")
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

				from("direct:soap").routeId("rota-soap")
					.to("xslt:pedido-para-soap.xslt")
					.log("${body}")
					.setHeader(Exchange.CONTENT_TYPE, constant("text/xml"))
					.to("http4://localhost:8080/webservices/financeiro");
			}
		});

		context.start();
		Thread.sleep(10000);
		context.stop();
	}
}
