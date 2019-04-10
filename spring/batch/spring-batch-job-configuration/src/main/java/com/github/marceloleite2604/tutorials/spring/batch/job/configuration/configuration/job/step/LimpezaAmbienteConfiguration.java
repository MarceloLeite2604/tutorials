package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.configuration.job.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.diversos.NomesBeans;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.DefinicoesJobCriacaoUsuarios;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.limpezaambiente.LimpezaAmbienteTasklet;

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
