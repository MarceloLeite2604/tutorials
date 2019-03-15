package org.marceloleite.tutorials.activemq;

import java.util.Optional;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class ReceptorMensagem {

	public static void main(String[] args) {

		ConnectionFactory connectionFactory = ActiveMQUtil.criarConnectionFactory();

		Connection connection = null;
		Session session = null;
		try {

			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(ActiveMQUtil.NOME_QUEUE);

			MessageConsumer messageConsumer = session.createConsumer(queue);

			connection.start();

			System.out.println("Verificando mensagem.");
			
			Optional<TextMessage> optionalTextMessage = Optional.ofNullable((TextMessage) messageConsumer.receive(3000L));
			if ( optionalTextMessage.isPresent()) {
				System.out.println("Mensagem recebida: " + optionalTextMessage.get().getText());
			} else {
				System.out.println("Nenhuma mensagem recebida.");
			}
			
		} catch (JMSException excecao) {
			throw new RuntimeException("Erro ao receber mensagem.", excecao);
		} finally {
			ActiveMQUtil.encerrarSessao(session);
			ActiveMQUtil.encerrarConexao(connection);
		}
	}
}
