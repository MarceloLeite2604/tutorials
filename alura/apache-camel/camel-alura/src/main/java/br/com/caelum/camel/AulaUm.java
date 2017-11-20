package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class AulaUm {

	public static void aulaUm() throws Exception {
		CamelContext context = new DefaultCamelContext();

		/* Implementado na primeira aula. */
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("file:pedidos?delay=5s&noop=true").log("${id}")
					.marshal()
					.xmljson()
					.log("${body}")
					.setHeader("CamelFileName", simple("${file:name.noext}.json"))
					.to("file:saida");
			}
		});

		context.start();
		Thread.sleep(10000);
		context.stop();
	}
}
