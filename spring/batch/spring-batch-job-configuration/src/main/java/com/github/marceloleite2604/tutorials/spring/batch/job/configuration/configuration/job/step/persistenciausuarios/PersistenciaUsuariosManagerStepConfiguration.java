package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.configuration.job.step.persistenciausuarios;

import javax.inject.Named;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.diversos.NomesBeans;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.DefinicoesJobCriacaoUsuarios;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.persistenciausuarios.PersistenciaUsuariosStepPartitioner;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.job.persistenciaclientes.PersistenciaUsuariosProperties;

@Configuration
public class PersistenciaUsuariosManagerStepConfiguration {

	@Bean(NomesBeans.PERSISTENCIA_USUARIOS_MANAGER_STEP)
	public Step criarPersistenciaUsuariosManagerStep(StepBuilderFactory stepBuilderFactory,
			@Named(NomesBeans.TASK_EXECUTOR_PERSISTENCIA_USUARIOS) TaskExecutor taskExecutor,
			PersistenciaUsuariosProperties persistenciaUsuariosProperties,
			PersistenciaUsuariosStepPartitioner persistenciaUsuariosStepPartitioner,
			@Named(NomesBeans.PERSISTENCIA_USUARIOS_WORKER_STEP) Step persistenciaUsuariosWorkerStep) {

		return stepBuilderFactory
				.get(DefinicoesJobCriacaoUsuarios.NOME_STEP_PERSISTENCIA_USUARIOS_MANAGER)
				.partitioner(DefinicoesJobCriacaoUsuarios.NOME_STEP_PERSISTENCIA_USUARIOS_WORKER,
						persistenciaUsuariosStepPartitioner)
				.taskExecutor(taskExecutor)
				.gridSize(persistenciaUsuariosProperties.getQuantidadeDeLeitores())
				.step(persistenciaUsuariosWorkerStep)

				.build();
	}

	@Bean(NomesBeans.TASK_EXECUTOR_PERSISTENCIA_USUARIOS)
	public TaskExecutor criarTaskExecutorPersistenciaUsuarios() {
		SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();

		simpleAsyncTaskExecutor.setThreadNamePrefix(
				DefinicoesJobCriacaoUsuarios.NOME_STEP_PERSISTENCIA_USUARIOS_WORKER + "-");

		return simpleAsyncTaskExecutor;
	}
}
