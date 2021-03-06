package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.persistenciausuarios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.exception.SpringBatchJobConfigurationRuntimeException;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.persistenciausuarios.contexto.PersistenciaClientesContextoPropriedade;

public class PersistenciaUsuariosStepExecutionListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// Não utilizado.
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		String caminhoArquivoTemporario = stepExecution.getExecutionContext()
				.getString(PersistenciaClientesContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS.getNome());
		try {
			Files.deleteIfExists(Paths.get(caminhoArquivoTemporario));
		} catch (IOException excecao) {
			throw new SpringBatchJobConfigurationRuntimeException(
					"Erro ao excluir o arquivo temporário \"" + caminhoArquivoTemporario + "\".",
					excecao);
		}
		return stepExecution.getExitStatus();
	}

}
