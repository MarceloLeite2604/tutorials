package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.configuration.job.step.persistenciausuarios;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.diversos.NomesBeans;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.DefinicoesJobCriacaoUsuarios;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.persistenciausuarios.PersistenciaUsuariosItemProcessor;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.persistenciausuarios.PersistenciaUsuariosItemStreamReader;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.persistenciausuarios.PersistenciaUsuariosItemStreamWriter;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.csv.UsuarioCsvVO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.po.UsuarioPO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.job.persistenciaclientes.PersistenciaUsuariosProperties;

@Component
public class PersistenciaUsuariosWorkerStepConfiguration {

	@Bean(NomesBeans.PERSISTENCIA_USUARIOS_WORKER_STEP)
	public Step criarPersistenciaUsuariosWorkerStep(StepBuilderFactory stepBuilderFactory,
			PersistenciaUsuariosProperties persistenciaUsuariosProperties,
			PersistenciaUsuariosItemStreamReader persistenciaUsuariosItemStreamReader,
			PersistenciaUsuariosItemProcessor persistenciaUsuariosItemProcessor,
			PersistenciaUsuariosItemStreamWriter persistenciaUsuariosItemStreamWriter) {

		return stepBuilderFactory
				.get(DefinicoesJobCriacaoUsuarios.NOME_STEP_PERSISTENCIA_USUARIOS_WORKER)
				.<UsuarioCsvVO, UsuarioPO>chunk(persistenciaUsuariosProperties.getTamanhoDoChunk())
				.reader(persistenciaUsuariosItemStreamReader)
				.processor(persistenciaUsuariosItemProcessor)
				.writer(persistenciaUsuariosItemStreamWriter)
				.build();
	}
}
