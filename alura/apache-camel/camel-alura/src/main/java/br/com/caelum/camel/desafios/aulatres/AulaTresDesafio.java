package br.com.caelum.camel.desafios.aulatres;

import java.text.SimpleDateFormat;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.xstream.XStreamDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.thoughtworks.xstream.XStream;

public class AulaTresDesafio {

	public static void aulaTresDesafio() throws Exception {

		final XStream xstream = new XStream();
		xstream.alias("negociacao", Negociacao.class);

		/*
		 * Criamos um registro que contém o data source que será utilizado para
		 * armazenar as informações no banco de dados.
		 */
		SimpleRegistry registro = new SimpleRegistry();
		registro.put("mysql", criaDataSource());
		CamelContext context = new DefaultCamelContext(registro);

		/* Implementado na primeira aula. */
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("timer://negociacoes?fixedRate=true&delay=1s&period=20s")
					.to("http4://argentumws.caelum.com.br/negociacoes")
					/*
					 * Converte o conteudo de InputStream para String para que
					 * não seja perdido o corpo da mensagem quando o InputStream
					 * for consumido.
					 */
					.convertBodyTo(String.class)
					/*
					 * Solicitamos o unmarshalling do String gerado informando o
					 * XStream que irá mapear o conteúdo do XML para a classe
					 * "Negociacao". O método irá retornar um List<Negociacao>.
					 */
					.unmarshal(new XStreamDataFormat(xstream))
					.split(body())
					/*
					 * Criamos um processor que recebe cada uma das mensagens
					 * (objetos "Negociacao" que foram divididos após o
					 * unmarshall). Cabe a este processor obter as informações
					 * de preço, quantidade e data de data uma das mensagens.
					 */
					.process(new Processor() {
						@Override
						public void process(Exchange exchange) throws Exception {
							Negociacao negociacao = exchange.getIn()
								.getBody(Negociacao.class);
							exchange.setProperty("preco", negociacao.getPreco());
							exchange.setProperty("quantidade", negociacao.getQuantidade());
							String data = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(negociacao.getData()
								.getTime());
							exchange.setProperty("data", data);
						}
					})
					/*
					 * Definimos o body de cada mensagem como uma operação de
					 * insert no banco de dados, repassando as propriedades
					 * previamente definidas.
					 */
					.setBody(simple(
							"insert into negociacao(preco, quantidade, data) values (${property.preco}, ${property.quantidade}, '${property.data}')"))
					/* A função "end" informa o fim da rota. */
					.log("${body}")
					/*
					 * Por fim, enviamos as operações para o banco de dados
					 * através do data source mysql.
					 */
					.to("jdbc:mysql");
			}
		});

		context.start();
		Thread.sleep(10000);
		context.stop();
	}

	/*
	 * Configuramos um data source para conectar no nosso banco de dados.
	 */
	private static MysqlConnectionPoolDataSource criaDataSource() {
		MysqlConnectionPoolDataSource mysqlDs = new MysqlConnectionPoolDataSource();
		mysqlDs.setDatabaseName("camel");
		mysqlDs.setServerName("localhost");
		mysqlDs.setPort(3306);
		mysqlDs.setUser("root");
		mysqlDs.setPassword("marcelo");
		return mysqlDs;
	}
}
