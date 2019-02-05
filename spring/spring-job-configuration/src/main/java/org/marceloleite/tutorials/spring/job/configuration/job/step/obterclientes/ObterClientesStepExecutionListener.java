package org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ObterClientesStepExecutionListener implements StepExecutionListener {

	@Value("${programa.caminhoArquivoSaida}")
	private String caminhoDoArquivo;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		excluirArquivoDeSaida();
	}

	private void excluirArquivoDeSaida() {
		try {
			Files.deleteIfExists(Paths.get(caminhoDoArquivo));
		} catch (IOException excecao) {
			throw new RuntimeException("Erro ao excluir o arquivo \"" + caminhoDoArquivo + "\".",
					excecao);
		}
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}

}
