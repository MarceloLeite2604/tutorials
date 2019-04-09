package org.marceloleite.tutorials.spring.batch.partitioner.configuration;

import javax.inject.Named;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

	@Bean("partitionerJob")
	public Job partitionerJob(JobBuilderFactory jobBuilderFactory, TaskExecutor taskExecutor,
			@Named("partitionStep") Step partitionStep) {
		return jobBuilderFactory.get("partitioningJob")
				.start(partitionStep)
				.build();
	}
}
