package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.configuration.job.step;

import javax.inject.Named;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.configuration.NomesBeans;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.DefinicoesJobCriacaoUsuarios;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.aquisicaousuarios.AquisicaoUsuariosItemProcessor;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.aquisicaousuarios.reader.AquisicaoUsuariosItemStreamReader;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.aquisicaousuarios.writer.AquisicaoUsuariosItemStreamWriter;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.csv.UsuarioCsvVO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.json.UserJsonVO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties.AquisicaoUsuariosProperties;

@Configuration
public class AquisicaoUsuariosStepConfiguration {

	@Bean
	@Named(NomesBeans.AQUISICAO_USUARIOS_STEP)
	public Step criarObterClientesStep(StepBuilderFactory stepBuilderFactory,
			AquisicaoUsuariosProperties aquisicaoUsuariosProperties,
			AquisicaoUsuariosItemStreamReader aquisicaoUsuariosItemStreamReader,
			AquisicaoUsuariosItemProcessor aquisicaoUsuariosItemProcessor,
			AquisicaoUsuariosItemStreamWriter aquisicaoUsuariosItemStreamWriter) {

		return stepBuilderFactory.get(DefinicoesJobCriacaoUsuarios.NOME_STEP_AQUISICAO_USUARIOS)
				.<UserJsonVO, UsuarioCsvVO>chunk(aquisicaoUsuariosProperties.getTamanhoDoLote())
				.reader(aquisicaoUsuariosItemStreamReader)
				.processor(aquisicaoUsuariosItemProcessor)
				.writer(aquisicaoUsuariosItemStreamWriter)
				.build();
	}
}
