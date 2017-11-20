package br.com.caelum.camel.desafios.aulaseis;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.processor.validation.SchemaValidationException;

public class AulaSeisDesafio {
	public static void aulaSeisDesafio() throws Exception {
		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				errorHandler(deadLetterChannel("file:erro").logExhaustedMessageHistory(true)
					.useOriginalMessage()
					.maximumRedeliveries(3)
					.maximumRedeliveryDelay(2000)
					.onRedelivery(new Processor() {

						@Override
						public void process(Exchange exchange) throws Exception {
							int counter = (int) exchange.getIn()
								.getHeader(Exchange.REDELIVERY_COUNTER);
							int max = (int) exchange.getIn()
								.getHeader(Exchange.REDELIVERY_MAX_COUNTER);
							System.out.println("Redelivery " + counter + "/" + max);
						}
					}));

				/*
				 * Através do onException, podemos definir tratamentos de erros
				 * específicos para cada exceção.
				 */
				onException(SchemaValidationException.class)
					/*
					 * Ao passar o parâmetro "true" para o método "handled", a
					 * mensagem é retirada de vez da rota onde ocorreu o erro.
					 * Se o parâmetro for "false", o Camel considera que a
					 * exceção foi tratada e a mensagem continuará na sua rota
					 * original.
					 */
					.handled(false)
					.maximumRedeliveries(3)
					.redeliveryDelay(2000)
					.onRedelivery(new Processor() {
						@Override
						public void process(Exchange exchange) throws Exception {
							int counter = (int) exchange.getIn()
								.getHeader(Exchange.REDELIVERY_COUNTER);
							int max = (int) exchange.getIn()
								.getHeader(Exchange.REDELIVERY_MAX_COUNTER);
							System.out.println("Redelivery (SchemaValidationException) - " + counter + "/" + max);
							;
						}
					})
					/*
					 * Uma vez que a mensagem tenha dado erro, é possível
					 * redirecioná-la para outra rota. Por exemplo uma pasta
					 * específica de erros de parse.
					 */
					.to("file:erro/parsing");

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
