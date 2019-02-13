package org.marceloleite.tutorials.spring.job.configuration.configuration.step.persistirclientes;

import javax.inject.Named;

import org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes.PersistirClientesProperties;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes.PersistirClientesStepPartitioner;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistirClientesManagerStepConfiguration {

	@Bean("persistirClientesManagerStep")
	public Step partitionStep(StepBuilderFactory stepBuilderFactory,
			PersistirClientesProperties persistirClientesProperties,
			PersistirClientesStepPartitioner persistirClientesStepPartitioner,
			@Named("persistirClientesWorkerStep") Step persistirClientesWorkerStep) {

		return stepBuilderFactory.get("persistirClientesManager")
				.partitioner("persistirClientesWorker", persistirClientesStepPartitioner)
				.gridSize(persistirClientesProperties.getQuantidadeDeLeitores())
				.step(persistirClientesWorkerStep)
				.build();
	}
}
