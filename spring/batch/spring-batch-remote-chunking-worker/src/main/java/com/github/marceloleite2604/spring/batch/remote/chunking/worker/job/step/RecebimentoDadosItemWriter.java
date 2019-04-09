package com.github.marceloleite2604.spring.batch.remote.chunking.worker.job.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class RecebimentoDadosItemWriter implements ItemWriter<String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecebimentoDadosItemWriter.class);

	@Override
	public void write(List<? extends String> textos) throws Exception {
		for (String texto : textos) {
			LOGGER.info("Escrevendo o texto \"{}\".", texto);
		}
	}

}
