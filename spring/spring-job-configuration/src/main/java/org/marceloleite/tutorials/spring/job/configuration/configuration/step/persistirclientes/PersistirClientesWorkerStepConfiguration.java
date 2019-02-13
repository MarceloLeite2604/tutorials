package org.marceloleite.tutorials.spring.job.configuration.configuration.step.persistirclientes;

import org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes.PersistirClientesItemProcessor;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes.PersistirClientesItemStreamReader;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes.PersistirClientesItemStreamWriter;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes.PersistirClientesProperties;
import org.marceloleite.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import org.marceloleite.tutorials.spring.job.configuration.model.po.UsuarioPO;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PersistirClientesWorkerStepConfiguration {

	@Bean("persistirClientesWorkerStep")
	public Step partitionStep(StepBuilderFactory stepBuilderFactory,
			PersistirClientesProperties persistirClientesProperties,
			PersistirClientesItemStreamReader persistirClientesItemStreamReader,
			PersistirClientesItemProcessor persistirClientesItemProcessor,
			PersistirClientesItemStreamWriter persistirClientesItemStreamWriter) {
		
		return stepBuilderFactory.get("parsistirClientesWorkerStep")
				.<UsuarioCsvVO, UsuarioPO>chunk(persistirClientesProperties.getTamanhoDoChunk())
				.reader(persistirClientesItemStreamReader)
				.processor(persistirClientesItemProcessor)
				.writer(persistirClientesItemStreamWriter)
				.build();
	}
}
