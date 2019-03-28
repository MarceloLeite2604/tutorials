package com.github.marceloleite2604.tutorials.spring.cloud.stream.client.inversor;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.cloud.stream.client.model.Mensagem;

@Component
@EnableBinding(Processor.class)
public class InversorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(InversorService.class);

	@Inject
	private Processor processor;

	public String inverter(String texto) throws Exception {
		LOGGER.debug("Enviando o texto \"{}\" para o serviço.", texto);
		processor.output()
				.send(MessageBuilder.withPayload(new Mensagem(texto))
						.build());

		TimeoutMessageHandler timeoutMessageHandler = new TimeoutMessageHandler();

		processor.input()
				.subscribe(timeoutMessageHandler);

		LOGGER.debug("Aguardando a resposta do servidor.");
		boolean response;
		try {
			response = timeoutMessageHandler.getCountDownLatch()
					.await(2, TimeUnit.SECONDS);

			String resultado = "";
			if (response) {
				resultado = timeoutMessageHandler.getTexto()
						.orElse("");
			} else {
				LOGGER.debug("Timeout ocorrido ao aguardar a resposta.");
			}
			return resultado;
		} catch (InterruptedException excecao) {
			throw new Exception("Erro ao aguardar a resposta do serviço.", excecao);
		} finally {
			processor.input()
					.unsubscribe(timeoutMessageHandler);
		}

	}
}
