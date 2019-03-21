package org.marceloleite.tutorials.spring.kafka.consumer;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class Main {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

		MessageListener messageListener = context.getBean(MessageListener.class);

		LOGGER.info("Aguardando mensagem.");
		boolean mensagemRecebida = messageListener.getCountDownLatch()
				.await(30, TimeUnit.SECONDS);

		if (!mensagemRecebida) {
			LOGGER.info("Nenhuma menssagem recebida.");
		}

		context.close();
	}
}
