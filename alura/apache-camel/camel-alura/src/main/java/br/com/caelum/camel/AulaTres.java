package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.impl.DefaultCamelContext;

public class AulaTres {
	public static void aulaTres() throws Exception {
		CamelContext context = new DefaultCamelContext();

		/* Implementado na primeira aula. */
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("file:pedidos?delay=5s&noop=true")
					/*
					 * Como o id do pedido está dentro no xpath
					 * "/pedido/id/text()", não temos este valor após realizar o
					 * split das mensagens. Logo, temos que obter este valor
					 * antes de realizar o split.
					 */
					.setProperty("pedidoId", xpath("/pedido/id/text()"))
					/*
					 * Assim como o id do pedido, também temos que obter o id do
					 * cliente antes de dividir a mensagem.
					 */
					.setProperty("clienteId", xpath("/pedido/pagamento/email-titular/text()"))
					.split()
					.xpath("/pedido/itens/item")
					.filter()
					.xpath("/item/formato[text()]='EBOOK'")
					/*
					 * Agora, uma vez que temos a mensagem dividida e filtrada,
					 * podemos obter o id do ebook.
					 */
					.setProperty("ebookId", xpath("/item/livro/codigo/text()"))
					.marshal()
					.xmljson()
					.log("${id} - ${body}")
					/*
					 * .setHeader(Exchange.FILE_NAME, simple(
					 * "${file:name.noext}-${header.CamelSplitIndex}.json"))
					 */
					/*
					 * Como vamos entregar o conteúdo para um serviço HTTP,
					 * vamos também definir o método utilizado para entregar a
					 * mensagem como "POST".
					 */
					/* .setHeader(Exchange.HTTP_METHOD, HttpMethods.POST) */
					/*
					 * Opcionalmente, podemos utilizar o método GET para
					 * entregar as mensagens.
					 */
					.setHeader(Exchange.HTTP_METHOD, HttpMethods.GET)
					/*
					 * A linha abaixo é o equivalente a executarmos a URL
					 * "http://localhost:8080/webservices/ebook/item?ebookid=
					 * ARQ&pedidoId=2451256&clientId=edgar.b@abc.com"
					 */
					/*
					 * .setHeader(Exchange.HTTP_QUERY, constant(
					 * "ebookId=ARQ&pedidoId=2451256&clienteId=edgar.b@abc.com")
					 * )
					 */
					/*
					 * Temos agora que preencher os parâmetros do método GET com
					 * as informações das mensagens.
					 */
					/*
					 * A primeira observação é a alteração do preenchimento do
					 * segundo parâmetro. Anteriormente era através do método
					 * "constant", agora é através do método "simple". Este
					 * método "simple" realiza a substituição das expression
					 * languages, já a função "constant" não faz isto.
					 */
					/*
					 * A segunda observação é que estamos substituindo os
					 * valores de preenchimento por expression languages,
					 * entretanto ainda não temos estas variáveis definidas.
					 * Logo, temos que definí-las.
					 */
					.setHeader(Exchange.HTTP_QUERY, simple(
							"ebookId=${property.ebookId}&pedidoId=${property.pedidoId}&clienteId=${property.clienteId}"))
					.to("http4://localhost:8080/webservices/ebook/item");
			}
		});

		context.start();
		Thread.sleep(10000);
		context.stop();
	}
}
