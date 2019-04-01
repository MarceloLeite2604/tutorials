package com.github.marceloleite2604.spring.batch.remote.chunking.configuration;

import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marceloleite2604.spring.batch.remote.chunking.job.step.EnvioDadosChunkMessageChannelItemWriter;
import com.github.marceloleite2604.spring.batch.remote.chunking.job.step.EnvioDadosItemStreamReader;
import com.github.marceloleite2604.spring.batch.remote.chunking.job.step.EnvioDadosJobExecutionListener;

@Configuration
public class EnvioDadosStepConfiguration {

	@Bean(NomesBeans.STEP_ENVIO_DADOS)
	public TaskletStep masterStep(StepBuilderFactory stepBuilderFactory,
			EnvioDadosItemStreamReader envioDadosItemStreamReader,
			EnvioDadosChunkMessageChannelItemWriter envioDAdosChunkMessageChannelItemWriter,
			EnvioDadosJobExecutionListener envioDadosJobExecutionListener) {
		return stepBuilderFactory.get("envio-dados-step")
				.<String, String>chunk(3)
				.reader(envioDadosItemStreamReader)
				.writer(envioDAdosChunkMessageChannelItemWriter)
				.build();
	}
}
