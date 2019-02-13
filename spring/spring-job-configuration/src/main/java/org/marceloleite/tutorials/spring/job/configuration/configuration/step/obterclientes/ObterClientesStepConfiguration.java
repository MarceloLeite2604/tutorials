package org.marceloleite.tutorials.spring.job.configuration.configuration.step.obterclientes;

import javax.inject.Named;

import org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.ObterClientesItemProcessor;
import org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.ObterClientesItemStreamReader;
import org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.ObterClientesItemStreamWriter;
import org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.ObterClientesProperties;
import org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.ObterClientesStepExecutionListener;
import org.marceloleite.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import org.marceloleite.tutorials.spring.job.configuration.model.json.UserJsonVO;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObterClientesStepConfiguration {

	@Bean
	@Named("obterClientesStep")
	public Step criarObterClientesStep(StepBuilderFactory stepBuilderFactory,
			ObterClientesProperties obterClientesProperties,
			ObterClientesStepExecutionListener obterClientesStepExecutionListener,
			ObterClientesItemStreamReader obterClientesReader,
			ObterClientesItemProcessor obterClientesItemProcessor,
			ObterClientesItemStreamWriter obterClientesWriter) {

		return stepBuilderFactory.get("obterClientes")
				.<UserJsonVO, UsuarioCsvVO>chunk(obterClientesProperties.getTamanhoDoChunk())
				.reader(obterClientesReader)
				.processor(obterClientesItemProcessor)
				.writer(obterClientesWriter)
				.listener(obterClientesStepExecutionListener)
				.build();
	}
}
