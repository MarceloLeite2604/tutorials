package br.com.caelum.camel.desafios.aulacinco;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class AulaCincoOpcional {
	public static void aulaCincoOpcional() throws Exception {
		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("direct:entrada").setHeader("data", constant("8/12/2015"))
					.to("velocity:template.vm")
					.log("${body}");
			}
		});

		context.start();
		/*
		 * O producer template é um produtor de conteúdo gerado após o início do
		 * context. Utilizando um nome pré-definido de rota (direct:entrada),
		 * podemos indicar a entrada de dados para o nosso serviço.
		 */
		ProducerTemplate producer = context.createProducerTemplate();
		producer.sendBody("direct:entrada", "Apache Camel rocks!!!");
		Thread.sleep(10000);
		context.stop();
	}
}
