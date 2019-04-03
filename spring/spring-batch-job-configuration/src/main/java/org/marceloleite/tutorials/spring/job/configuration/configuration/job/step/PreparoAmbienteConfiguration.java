package org.marceloleite.tutorials.spring.job.configuration.configuration.job.step;

import org.marceloleite.tutorials.spring.job.configuration.diversos.NomesBeans;
import org.marceloleite.tutorials.spring.job.configuration.job.DefinicoesJobCriacaoUsuarios;
import org.marceloleite.tutorials.spring.job.configuration.job.step.preparoambiente.PreparoAmbienteTasklet;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PreparoAmbienteConfiguration {

	@Bean(NomesBeans.PREPARO_AMBIENTE_STEP)
	public Step criarPrepararAmbienteStep(StepBuilderFactory stepBuilderFactory,
			PreparoAmbienteTasklet prepararAmbienteTasklet) {
		return stepBuilderFactory.get(DefinicoesJobCriacaoUsuarios.NOME_STEP_PREPARO_AMBIENTE)
				.tasklet(prepararAmbienteTasklet)
				.build();
	}
}
