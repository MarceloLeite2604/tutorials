package org.marceloleite.tutorials.spring.job.configuration.configuration.job.step.persistenciausuarios;

import org.marceloleite.tutorials.spring.job.configuration.diversos.NomesBeans;
import org.marceloleite.tutorials.spring.job.configuration.job.DefinicoesJobCriacaoUsuarios;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistenciausuarios.PersistenciaUsuariosItemProcessor;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistenciausuarios.PersistenciaUsuariosItemStreamReader;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistenciausuarios.PersistenciaUsuariosItemStreamWriter;
import org.marceloleite.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import org.marceloleite.tutorials.spring.job.configuration.model.po.UsuarioPO;
import org.marceloleite.tutorials.spring.job.configuration.propriedade.job.persistenciaclientes.PersistenciaUsuariosProperties;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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
