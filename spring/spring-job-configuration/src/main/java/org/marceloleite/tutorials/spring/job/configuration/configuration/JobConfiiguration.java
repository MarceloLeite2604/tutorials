package org.marceloleite.tutorials.spring.job.configuration.configuration;

import javax.inject.Named;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiiguration {

	@Bean
	public Job criarJob(JobBuilderFactory jobBuilderFactory,
			@Named("obterClientesStep") Step obterClientesStep) {
		return jobBuilderFactory.get("spring-job-configuration")
				.start(obterClientesStep)
				.build();
	}
}
