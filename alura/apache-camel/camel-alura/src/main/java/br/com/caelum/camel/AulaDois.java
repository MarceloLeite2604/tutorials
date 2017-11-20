package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class AulaDois {
	public static void aulaDois() throws Exception {
		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("file:pedidos?delay=5s&noop=true")
					/*
					 * No caso do arquivo "2_pedido.xml" ele está passando até os pedidos que são no
					 * formato impresso porque, de acordo com o nosso filtro, o arquivo é aceito se
					 * ele possuir PELO MENOS UM pedido do tipo "EBOOK". Logo é necessário dividir
					 * os arquivos em submensagens para então obter somente os pedidos do tipo
					 * "eBook".
					 */
					.split()
					.xpath("/pedido/itens/item")
					.filter()
					/*
					 * Dentro dos XMLs lidos, realiza uma filtragem buscando somente os pedidos que
					 * tenham o formato "EBOOK".
					 */
					/* .xpath("/pedido/itens/item/formato[text()]='EBOOK'") */
					/*
					 * Uma vez que o XML foi dividido através do "split", o xpath original não
					 * funciona mais, pois o ponto inicial do XML não é mais "pedidos", mas sim
					 * "item". Por isso temos que reformular o xpath.
					 */
					.xpath("/item/formato[text()]='EBOOK'")
					.marshal()
					.xmljson()
					.log("${id} - ${body}")
					/*
					 * Se houver um caso onde, no mesmo XML, existam vários pedidos que atendam os
					 * requisitos de filtragem, eles serão escritos no mesmo arquivo de saída. Como
					 * os items foram divididos através do "split", eles serão escritos no mesmo
					 * arquivo de saída. No final, somente o último item constará no arquivo. Para
					 * evitar este problema, alteramos o nome do arquivo de saída da mensagem para
					 * identificá-lo unicamente.
					 */
					/* .setHeader("CamelFileName", simple("${file:name.noext}.json")) */
					/*
					 * Além disto, também é possível melhorar o formato do nome do arquivo de saída
					 * do arquivo e utilizar as constantes que identificam as propriedades de header
					 * da mensagem.
					 */
					/* .setHeader("CamelFileName", simple("${id}.json")) */
					.setHeader(Exchange.FILE_NAME, simple("${file:name.noext}-${header.CamelSplitIndex}.json"))
					.to("file:saida");
			}
		});

		context.start();
		Thread.sleep(10000);
		context.stop();
	}

}
