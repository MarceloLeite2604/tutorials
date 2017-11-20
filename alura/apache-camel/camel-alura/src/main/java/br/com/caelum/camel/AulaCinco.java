package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.impl.DefaultCamelContext;

public class AulaCinco {
	public static void aulaCinco() throws Exception {
		CamelContext context = new DefaultCamelContext();

		/* Implementado na primeira aula. */
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("file:pedidos?delay=5s&noop=true").routeId("rota-pedidos")
					.multicast()
					.parallelProcessing()
					.timeout(1000)
					.to("direct:soap")
					.to("direct:http");

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
					/*
					 * Antes de encaminhar o mensagem para o servidor através do
					 * SOAP, vamos utilizar o XSLT para escrever o XML de
					 * requisição.
					 */
					/*
					 * Observação 1: O XSLT (eXtensible stylesheet language) é
					 * um XML que descreve a elaboração de outro XML, definido a
					 * sua estrutura e obtendo os valores através de outro XML
					 * utilizando o XPath.
					 */
					/*
					 * Observação 2: O XML requisitado para encaminhar os dados
					 * para o serviço foi obtido importando o WSDL do serviço no
					 * SoapUI. Com base nele foi montado o XSLT que elabora o
					 * XML necessário para a requisição.
					 */
					.to("xslt:pedido-para-soap.xslt")
					.log("${body}")
					.setHeader(Exchange.CONTENT_TYPE, constant("text/xml"))
					/*
					 * Observação 3: Uma requisição SOAP é sempre através do
					 * método POST. Como não definimos nada, o Camel define que
					 * o método utilizado é POST, então está tudo ok.
					 */
					.to("http4://localhost:8080/webservices/financeiro");
			}
		});

		context.start();
		Thread.sleep(10000);
		context.stop();
	}
}
