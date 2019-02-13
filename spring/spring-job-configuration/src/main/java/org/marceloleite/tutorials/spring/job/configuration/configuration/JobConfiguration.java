package org.marceloleite.tutorials.spring.job.configuration.configuration;

import javax.inject.Named;

import org.marceloleite.tutorials.spring.job.configuration.UsuarioWebBuffer;
import org.marceloleite.tutorials.spring.job.configuration.csv.writer.UsuarioCsvWriter;
import org.marceloleite.tutorials.spring.job.configuration.job.MeuJobExecutionListener;
import org.marceloleite.tutorials.spring.job.configuration.job.MeuJobProperties;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

	public static final String NOME_DO_JOB = "meuJob";

	@Bean(NOME_DO_JOB)
	public Job criarJob(JobBuilderFactory jobBuilderFactory,
			@Named("obterClientesStep") Step obterClientesStep,
			MeuJobExecutionListener meuJobExecutionListener,
			@Named("persistirClientesManagerStep") Step persistirClientesManagerStep) {
		return jobBuilderFactory.get(NOME_DO_JOB)
				.start(obterClientesStep)
				.listener(meuJobExecutionListener)
				.next(persistirClientesManagerStep)
				.build();
	}

	@Bean
	public UsuarioWebBuffer criarUsuarioWebBuffer(MeuJobProperties meuJobProperties) {
		return new UsuarioWebBuffer(meuJobProperties.getObterClientesProperties()
				.getTamanhoDoChunk());
	}

	@Bean
	public UsuarioCsvWriter criarUsuarioCvsWriter(MeuJobProperties meuJobProperties) {
		return new UsuarioCsvWriter(meuJobProperties.getCaminhoArquivoDeUsuarios());
	}
}
