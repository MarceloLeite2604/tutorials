package com.github.marceloleite2604.spring.batch.remote.chunking.job.step;

import java.util.Arrays;
import java.util.Iterator;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.stereotype.Component;

@Component
public class EnvioDadosItemStreamReader implements ItemStreamReader<String> {

	private static final String[] MENSAGENS = { "A", "B", "C", "D", "E", "F", "G" };

	private Iterator<String> iteratorMensagens;

	@Override
	public void open(ExecutionContext executionContext) {
		iteratorMensagens = Arrays.asList(MENSAGENS)
				.iterator();
	}

	@Override
	public void update(ExecutionContext executionContext) {
		// Não utilizado.
	}

	@Override
	public void close() {
		// Não utilizado.
	}

	@Override
	public String read() throws Exception {
		if (iteratorMensagens.hasNext()) {
			return iteratorMensagens.next();
		} else {
			return null;
		}
	}

}
