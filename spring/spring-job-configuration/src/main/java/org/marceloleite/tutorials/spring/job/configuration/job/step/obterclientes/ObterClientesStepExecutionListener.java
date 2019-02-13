package org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.job.MeuJobProperties;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class ObterClientesStepExecutionListener implements StepExecutionListener {

	@Inject
	private MeuJobProperties meuJobProperties;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		excluirArquivoUsuarios();
		System.out.println("Arquivo de usuários excluído.");
	}

	private void excluirArquivoUsuarios() {
		String caminhoArquivoUsuarios = meuJobProperties.getCaminhoArquivoDeUsuarios();
		try {
			Files.deleteIfExists(Paths.get(caminhoArquivoUsuarios));
		} catch (IOException excecao) {
			throw new RuntimeException(
					"Erro ao excluir o arquivo \"" + caminhoArquivoUsuarios + "\".", excecao);
		}
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("AfterStep");
		return stepExecution.getExitStatus();
	}
}
