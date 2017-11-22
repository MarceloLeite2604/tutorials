package br.com.alura.enviadoremail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

/*
 * Informa para o spring que esta classe é um serviço.
 */
@Service
public class EmailService {

	public void enviar(String nome, String emailDestinatario) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("marcelo.leite.alura@gmail.com", "michael01jackson"));
			email.setSSLOnConnect(true);
			
			email.setFrom("contato@alura.com.br");
			email.setSubject("Você foi convidado pelo ListaVIP.");
			email.setMsg("Olá " + nome + ". Você acaba de ser condidado pelo ListaVIP.");
			email.addTo(emailDestinatario);
			email.send();
		} catch(EmailException e) {
			e.printStackTrace();
		}
	}
}
