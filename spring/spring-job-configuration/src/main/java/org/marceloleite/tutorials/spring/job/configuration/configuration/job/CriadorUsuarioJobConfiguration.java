package org.marceloleite.tutorials.spring.job.configuration.configuration.job;

import javax.inject.Named;

import org.marceloleite.tutorials.spring.job.configuration.diversos.NomesBeans;
import org.marceloleite.tutorials.spring.job.configuration.job.CriadorUsuariosJobExecutionListener;
import org.marceloleite.tutorials.spring.job.configuration.job.DefinicoesJobCriacaoUsuarios;
import org.marceloleite.tutorials.spring.job.configuration.job.step.preparoambiente.PreparoAmbienteTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class CriadorUsuarioJobConfiguration {

	@Bean(NomesBeans.CRIADOR_USUARIOS_JOB)
	public Job criarJob(JobBuilderFactory jobBuilderFactory,
			PreparoAmbienteTasklet preparoAmbienteTasklet,
			@Named(NomesBeans.PREPARO_AMBIENTE_STEP) Step preparoAmbienteStep,
			@Named(NomesBeans.AQUISICAO_USUARIOS_STEP) Step aquisicaoUsuariosStep,
			@Named(NomesBeans.PERSISTENCIA_USUARIOS_MANAGER_STEP) Step persistenciaUsuariosManagerStep,
			@Named(NomesBeans.LIMPEZA_AMBIENTE_STEP) Step limpezaAmbienteStep,
			CriadorUsuariosJobExecutionListener criadorUsuariosJobExecutionListener) {
		return jobBuilderFactory.get(DefinicoesJobCriacaoUsuarios.NOME_DO_JOB)
				.start(preparoAmbienteStep)
				.next(aquisicaoUsuariosStep)
				.next(persistenciaUsuariosManagerStep)
				.next(limpezaAmbienteStep)
				.listener(criadorUsuariosJobExecutionListener)
				.build();
	}
}
