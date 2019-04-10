package com.github.marceloleite2604.tutorials.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class EmissorMensagem {

	public static void main(String[] args) {

		ConnectionFactory connectionFactory = ActiveMQUtil.criarConnectionFactory();

		Connection connection = null;
		Session session = null;

		try {
			connection = connectionFactory.createConnection();
			connection.start();

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(ActiveMQUtil.NOME_QUEUE);

			MessageProducer messageProducer = session.createProducer(destination);

			TextMessage textMessage = session.createTextMessage("Hello, world.");

			messageProducer.send(textMessage);
		} catch (JMSException excecao) {
			throw new RuntimeException("Erro ao enviar uma mensagem para o servidor.");
		} finally {
			ActiveMQUtil.encerrarSessao(session);
			ActiveMQUtil.encerrarConexao(connection);
		}

		System.out.println("Mensagem enviada.");
	}
}