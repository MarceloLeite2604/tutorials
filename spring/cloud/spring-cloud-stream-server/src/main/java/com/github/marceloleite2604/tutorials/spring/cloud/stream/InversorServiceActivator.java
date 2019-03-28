package com.github.marceloleite2604.tutorials.spring.cloud.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;

import com.github.marceloleite2604.tutorials.spring.cloud.stream.model.Mensagem;

@EnableBinding(Processor.class)
public class InversorServiceActivator {

	private static final Logger LOGGER = LoggerFactory.getLogger(InversorServiceActivator.class);

	@ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public Mensagem inverter(Mensagem mensagem) {

		LOGGER.info("Recebido o seguinte texto: {}", mensagem.getTexto());

		mensagem.setTexto(inverterTexto(mensagem.getTexto()));

		LOGGER.info("Retornando a seguinte resposta: \"{}\".", mensagem.getTexto());
		return mensagem;
	}

	private String inverterTexto(String texto) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int indice = texto.length() - 1; indice >= 0; indice--) {
			stringBuilder.append(texto.charAt(indice));
		}
		return stringBuilder.toString();
	}
}
