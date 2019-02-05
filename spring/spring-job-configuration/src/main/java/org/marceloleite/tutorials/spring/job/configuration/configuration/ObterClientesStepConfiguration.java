package org.marceloleite.tutorials.spring.job.configuration.configuration;

import javax.inject.Named;

import org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.ObterClientesItemProcessor;
import org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.ObterClientesItemReader;
import org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.ObterClientesItemWriter;
import org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.ObterClientesStepExecutionListener;
import org.marceloleite.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import org.marceloleite.tutorials.spring.job.configuration.model.json.UserJsonVO;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObterClientesStepConfiguration {

	@Value("${programa.tamanhoDoChunk}")
	private int tamanhoDoChunk;

	@Bean
	@Named("obterClientesStep")
	public Step criarObterClientesStep(StepBuilderFactory stepBuilderFactory,
			ObterClientesStepExecutionListener obterClientesStepExecutionListener,
			ObterClientesItemReader obterClientesReader,
			ObterClientesItemProcessor obterClientesItemProcessor,
			ObterClientesItemWriter obterClientesWriter) {
		return stepBuilderFactory.get("obterClientes")
				.<UserJsonVO, UsuarioCsvVO>chunk(tamanhoDoChunk)
				.reader(obterClientesReader)
				.processor(obterClientesItemProcessor)
				.writer(obterClientesWriter)
				.listener(obterClientesStepExecutionListener)
				.build();
	}
}
