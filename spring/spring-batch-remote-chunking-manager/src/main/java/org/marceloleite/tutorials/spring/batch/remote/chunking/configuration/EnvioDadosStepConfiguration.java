package org.marceloleite.tutorials.spring.batch.remote.chunking.configuration;

import org.marceloleite.tutorials.spring.batch.remote.chunking.job.step.EnvioDadosItemStreamReader;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvioDadosStepConfiguration {

	@Bean(NomesBeans.STEP_ENVIO_DADOS)
	public TaskletStep masterStep(StepBuilderFactory stepBuilderFactory,
			EnvioDadosItemStreamReader envioDadosItemStreamReader,
			ItemWriter<String> itemWriter) {
		return stepBuilderFactory.get("envio-dados-step")
				.<String, String>chunk(3)
				.reader(envioDadosItemStreamReader)
				.writer(itemWriter)
				.build();
	}
}
