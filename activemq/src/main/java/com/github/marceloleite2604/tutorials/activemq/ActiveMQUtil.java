package com.github.marceloleite2604.tutorials.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public final class ActiveMQUtil {

	public static final String NOME_QUEUE = "exemplo-queue";

	private ActiveMQUtil() {
		// Construtor privado para evitar a criação de objetos deste tipo.
	}

	public static ConnectionFactory criarConnectionFactory() {
		return new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_BROKER_URL);
	}
	
	public static void encerrarConexao(Connection connection) {
		if (connection != null) {
			try {
			connection.close();
			} catch (JMSException excecao) {
				throw new RuntimeException("Erro ao encerrar a conexão com o servidor JMS.", excecao);
			}
		}
	}
	
	public static void encerrarSessao(Session session) {
		if (session != null) {
			try {
			session.close();
			} catch (JMSException excecao) {
				throw new RuntimeException("Erro ao encerrar a sessão de conexão.", excecao);
			}
		}
	}
}
