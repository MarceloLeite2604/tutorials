package org.marceloleite.tutorials.spring.batch.partitioner.configuration;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import org.marceloleite.tutorials.spring.batch.partitioner.CustomMultiResourcePartitioner;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class MasterConfiguration {

	@Inject
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Partitioner partitioner() {
		CustomMultiResourcePartitioner partitioner = new CustomMultiResourcePartitioner();
		Resource[] resources;
		try {
			resources = new PathMatchingResourcePatternResolver()
					.getResources("file:src/main/resources/input/*.csv");
		} catch (IOException exception) {
			throw new RuntimeException("I/O problems when resolving the input file pattern.",
					exception);
		}
		partitioner.setResources(resources);
		return partitioner;
	}

	@Bean("partitionStep")
	public Step partitionStep(TaskExecutor taskExecutor, Partitioner partitioner,
			@Named("slaveStep") Step slaveStep) {
		return stepBuilderFactory.get("partitionStep")
				.partitioner("slaveStep", partitioner)
				.step(slaveStep)
				.taskExecutor(taskExecutor)
				.build();
	}
}
