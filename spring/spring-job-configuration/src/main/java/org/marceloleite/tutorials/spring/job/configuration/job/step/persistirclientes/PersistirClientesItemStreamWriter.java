package org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes;

import java.util.List;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.bo.UsuarioBO;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes.contexto.PersistirClientesContexto;
import org.marceloleite.tutorials.spring.job.configuration.model.po.UsuarioPO;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.stereotype.Component;

@Component
public class PersistirClientesItemStreamWriter implements ItemStreamWriter<UsuarioPO> {

	@Inject
	private UsuarioBO usuarioBO;

	@Inject
	private PersistirClientesContexto contextoExecucao;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void write(List<? extends UsuarioPO> usuarioPOs) throws Exception {
		usuarioBO.salvarTodos((List<UsuarioPO>) usuarioPOs);
		contextoExecucao
				.setRegistrosEscritos(contextoExecucao.getRegistrosEscritos() + usuarioPOs.size());
	}

}
