package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.enumeration.CriadorUsuariosContextoPropriedade;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class CriadorUsuariosJobExecutionListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		definirContextoExecucao(jobExecution);
	}

	private void definirContextoExecucao(JobExecution jobExecution) {
		JobParameters jobParameters = jobExecution.getJobParameters();
		ExecutionContext executionContext = jobExecution.getExecutionContext();
		String instante = jobParameters.getString(CriadorUsuariosContextoPropriedade.INSTANTE.getNome());
		executionContext.put(CriadorUsuariosContextoPropriedade.INSTANTE.getNome(), instante);
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// Não utilizado.
	}

}
