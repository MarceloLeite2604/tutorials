package org.marceloleite.tutorials.spring.batch.partitioner.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class BatchConfiguration {

	@Bean
	public TaskExecutor taskExecutor() {
		return new SimpleAsyncTaskExecutor();
		/*
		 * ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		 * executor.setCorePoolSize(5); executor.setMaxPoolSize(10);
		 * executor.setQueueCapacity(25); return executor;
		 */
	}

	
}
