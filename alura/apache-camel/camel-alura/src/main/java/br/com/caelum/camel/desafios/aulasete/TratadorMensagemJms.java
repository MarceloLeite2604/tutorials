package br.com.caelum.camel.desafios.aulasete;

import javax.jms.Message;
import javax.jms.MessageListener;

public class TratadorMensagemJms implements MessageListener {
	   public void onMessage(Message jmsMessage) {
	       System.out.println("Mensagem recebida.");
	   }
	}