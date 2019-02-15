package org.marceloleite.tutorials.spring.job.configuration.configuration.job.step;

import javax.inject.Named;

import org.marceloleite.tutorials.spring.job.configuration.diversos.NomesBeans;
import org.marceloleite.tutorials.spring.job.configuration.job.DefinicoesJobCriacaoUsuarios;
import org.marceloleite.tutorials.spring.job.configuration.job.step.aquisicaousuarios.AquisicaoUsuariosItemProcessor;
import org.marceloleite.tutorials.spring.job.configuration.job.step.aquisicaousuarios.reader.AquisicaoUsuariosItemStreamReader;
import org.marceloleite.tutorials.spring.job.configuration.job.step.aquisicaousuarios.writer.AquisicaoUsuariosItemStreamWriter;
import org.marceloleite.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import org.marceloleite.tutorials.spring.job.configuration.model.json.UserJsonVO;
import org.marceloleite.tutorials.spring.job.configuration.propriedade.job.aquisicaousuarios.AquisicaoUsuariosProperties;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AquisicaoUsuariosStepConfiguration {

	@Bean
	@Named(NomesBeans.AQUISICAO_USUARIOS_STEP)
	public Step criarObterClientesStep(StepBuilderFactory stepBuilderFactory,
			AquisicaoUsuariosProperties obterClientesProperties,
			AquisicaoUsuariosItemStreamReader obterClientesReader,
			AquisicaoUsuariosItemProcessor obterClientesItemProcessor,
			AquisicaoUsuariosItemStreamWriter obterClientesWriter) {

		return stepBuilderFactory.get(DefinicoesJobCriacaoUsuarios.NOME_STEP_AQUISICAO_USUARIOS)
				.<UserJsonVO, UsuarioCsvVO>chunk(obterClientesProperties.getTamanhoDoChunk())
				.reader(obterClientesReader)
				.processor(obterClientesItemProcessor)
				.writer(obterClientesWriter)
				.build();
	}
}
