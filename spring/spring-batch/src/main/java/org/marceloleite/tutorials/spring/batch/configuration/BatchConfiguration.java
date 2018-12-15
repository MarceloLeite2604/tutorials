package org.marceloleite.tutorials.spring.batch.configuration;

import javax.inject.Inject;
import javax.inject.Named;

import org.marceloleite.tutorials.spring.batch.job.CompletionNotificationJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Inject
    private JobBuilderFactory jobBuilderFactory;
	
	@Named("stepOne")
	@Inject
	private Step stepOne;

	@Bean
    public Job createImportUserJob(CompletionNotificationJobListener listener) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(stepOne)
            .end()
            .build();
    }
}