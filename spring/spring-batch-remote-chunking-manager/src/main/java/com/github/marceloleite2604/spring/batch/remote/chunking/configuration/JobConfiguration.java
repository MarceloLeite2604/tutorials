package com.github.marceloleite2604.spring.batch.remote.chunking.configuration;

import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marceloleite2604.spring.batch.remote.chunking.job.step.EnvioDadosJobExecutionListener;

@Configuration
@EnableBatchProcessing
public class JobConfiguration extends DefaultBatchConfigurer {

	@Bean(NomesBeans.JOB_REMOTE_CHUNKING)
	public Job criarJobRemoteChunking(JobBuilderFactory jobBuilderFactory,
			@Named(NomesBeans.STEP_ENVIO_DADOS) Step stepEnvioDados,
			EnvioDadosJobExecutionListener envioDadosJobExecutionListener) {
		return jobBuilderFactory.get("spring-batch-remote-chunking-manager")
				.start(stepEnvioDados)
				.listener(envioDadosJobExecutionListener)
				.build();
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		setDataSource(null);
	}
}
