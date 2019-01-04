package org.marceloleite.tutorials.spring.batch.configuration;

import javax.inject.Named;

import org.marceloleite.tutorials.spring.batch.job.CompletionNotificationJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Bean
	public TaskExecutor taskExecutor() {
		return new SimpleAsyncTaskExecutor();
	}

	@Bean
	public Job createImportUserJob(JobBuilderFactory jobBuilderFactory,
			@Named("stepOne") Step stepOne, CompletionNotificationJobListener completionNotificationJobListener) {
		return jobBuilderFactory.get("importUserJob")
				.incrementer(new RunIdIncrementer())
				.listener(completionNotificationJobListener)
				.flow(stepOne)
				.end()
				.build();
	}
}