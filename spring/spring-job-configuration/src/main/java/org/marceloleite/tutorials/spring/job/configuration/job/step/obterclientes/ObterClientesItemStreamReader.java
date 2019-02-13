package org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.UsuarioWebBuffer;
import org.marceloleite.tutorials.spring.job.configuration.job.step.obterclientes.contexto.ObterClientesContexto;
import org.marceloleite.tutorials.spring.job.configuration.model.json.UserJsonVO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class ObterClientesItemStreamReader implements ItemStreamReader<UserJsonVO> {

	@Inject
	private UsuarioWebBuffer usuarioWebBuffer;

	@Inject
	private ObterClientesContexto contextoExecucao;

	@Override
	public UserJsonVO read() throws Exception {

		UserJsonVO userJsonVO = null;
		if (!totalDeRegistrosAtingido()) {
			userJsonVO = usuarioWebBuffer.read();
			contextoExecucao.setRegistrosEscritos(contextoExecucao.getRegistrosEscritos() + 1);
		}
		return userJsonVO;
	}

	private boolean totalDeRegistrosAtingido() {
		return (contextoExecucao.getRegistrosEscritos() >= contextoExecucao.getTotalDeRegistros());
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		contextoExecucao.restaurarContexto(executionContext);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		contextoExecucao.salvarContexto(executionContext);

	}

	@Override
	public void close() throws ItemStreamException {
		// Não utilizado.
	}
}
