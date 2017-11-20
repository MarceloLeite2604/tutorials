package br.com.caelum.camel.desafios.aulacinco;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class AulaCincoDesafio {

	public static void aulaCincoDesafio() throws Exception {
		CamelContext context = new DefaultCamelContext();

		/* Implementado na primeira aula. */
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("file:movimentacoes?delay=5s&noop=true").to("xslt:movimentacoes-para-http.xslt")
				.setHeader(Exchange.FILE_NAME, simple("${file:name.noext}.html"))
					.to("file:saida");
			}
		});
		
		context.start();
		Thread.sleep(10000);
		context.stop();
	}

}
