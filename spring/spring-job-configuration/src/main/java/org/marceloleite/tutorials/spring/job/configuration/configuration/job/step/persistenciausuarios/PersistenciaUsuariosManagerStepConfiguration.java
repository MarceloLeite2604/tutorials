package org.marceloleite.tutorials.spring.job.configuration.configuration.job.step.persistenciausuarios;

import javax.inject.Named;

import org.marceloleite.tutorials.spring.job.configuration.diversos.NomesBeans;
import org.marceloleite.tutorials.spring.job.configuration.job.DefinicoesJobCriacaoUsuarios;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistenciausuarios.PersistenciaUsuariosStepPartitioner;
import org.marceloleite.tutorials.spring.job.configuration.propriedade.job.persistenciaclientes.PersistenciaUsuariosProperties;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenciaUsuariosManagerStepConfiguration {

	@Bean(NomesBeans.PERSISTENCIA_USUARIOS_MANAGER_STEP)
	public Step criarPersistenciaUsuariosManagerStep(StepBuilderFactory stepBuilderFactory,
			PersistenciaUsuariosProperties persistenciaUsuariosProperties,
			PersistenciaUsuariosStepPartitioner persistenciaUsuariosStepPartitioner,
			@Named(NomesBeans.PERSISTENCIA_USUARIOS_WORKER_STEP) Step persistenciaUsuariosWorkerStep) {

		return stepBuilderFactory
				.get(DefinicoesJobCriacaoUsuarios.NOME_STEP_PERSISTENCIA_USUARIOS_MANAGER)
				.partitioner(DefinicoesJobCriacaoUsuarios.NOME_STEP_PERSISTENCIA_USUARIOS_WORKER,
						persistenciaUsuariosStepPartitioner)
				.gridSize(persistenciaUsuariosProperties.getQuantidadeDeLeitores())
				.step(persistenciaUsuariosWorkerStep)
				.build();
	}
}
