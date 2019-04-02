package com.github.marceloleite2604.spring.batch.remote.chunking.worker.job.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class RecebimentoDadosItemProcessor implements ItemProcessor<String, String> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RecebimentoDadosItemProcessor.class);

	@Override
	public String process(String texto) throws Exception {
		LOGGER.info("Processando o texto \"{}\".", texto);
		return texto;
	}

}
