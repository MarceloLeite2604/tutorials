package org.marceloleite.tutorials.spring.job.configuration.job.step.aquisicaousuarios.reader;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.bo.UsuarioWebBO;
import org.marceloleite.tutorials.spring.job.configuration.job.step.aquisicaousuarios.contexto.AquisicaoUsuariosContexto;
import org.marceloleite.tutorials.spring.job.configuration.model.json.UserJsonVO;
import org.marceloleite.tutorials.spring.job.configuration.propriedade.job.aquisicaousuarios.AquisicaoUsuariosProperties;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class AquisicaoUsuariosItemStreamReader implements ItemStreamReader<UserJsonVO> {

	@Inject
	private AquisicaoUsuariosContexto contexto;

	@Inject
	private AquisicaoUsuariosProperties aquisicaoUsuariosProperties;
	
	@Inject
	private UsuarioWebBO usuarioWebBO;

	private UsuarioWebBuffer usuarioWebBuffer;

	@Override
	public UserJsonVO read() throws Exception {

		UserJsonVO userJsonVO = null;
		if (!totalDeRegistrosAtingido()) {
			userJsonVO = usuarioWebBuffer.read();
			contexto.setRegistrosEscritos(contexto.getRegistrosEscritos() + 1);
		}
		return userJsonVO;
	}

	private boolean totalDeRegistrosAtingido() {
		return (contexto.getRegistrosEscritos() >= contexto.getTotalDeRegistros());
	}

	@Override
	public void open(ExecutionContext executionContext) {
		contexto.restaurarContexto(executionContext);
		usuarioWebBuffer = new UsuarioWebBuffer(usuarioWebBO, aquisicaoUsuariosProperties.getTamanhoDoChunk());
	}

	@Override
	public void update(ExecutionContext executionContext) {
		contexto.salvarContexto(executionContext);

	}

	@Override
	public void close() {
		// Não utilizado.
	}
}
