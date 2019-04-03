package org.marceloleite.tutorials.spring.job.configuration.configuration.job.step;

import org.marceloleite.tutorials.spring.job.configuration.diversos.NomesBeans;
import org.marceloleite.tutorials.spring.job.configuration.job.DefinicoesJobCriacaoUsuarios;
import org.marceloleite.tutorials.spring.job.configuration.job.step.limpezaambiente.LimpezaAmbienteTasklet;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LimpezaAmbienteConfiguration {

	@Bean(NomesBeans.LIMPEZA_AMBIENTE_STEP)
	public Step criarLimpezaAmbienteStep(StepBuilderFactory stepBuilderFactory,
			LimpezaAmbienteTasklet limpezaAmbienteTasklet) {
		return stepBuilderFactory.get(DefinicoesJobCriacaoUsuarios.NOME_STEP_LIMPEZA_AMBIENTE)
				.tasklet(limpezaAmbienteTasklet)
				.build();
	}
}
