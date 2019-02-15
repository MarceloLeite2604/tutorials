package org.marceloleite.tutorials.spring.job.configuration.job.step.persistenciausuarios;

import java.util.List;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.bo.UsuarioBO;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistenciausuarios.contexto.PersistenciaClientesContexto;
import org.marceloleite.tutorials.spring.job.configuration.model.po.UsuarioPO;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.stereotype.Component;

@Component
public class PersistenciaUsuariosItemStreamWriter implements ItemStreamWriter<UsuarioPO> {

	@Inject
	private UsuarioBO usuarioBO;

	@Inject
	private PersistenciaClientesContexto contextoExecucao;

	@Override
	public void open(ExecutionContext executionContext) {
		contextoExecucao.restaurarContexto(executionContext);
	}

	@Override
	public void update(ExecutionContext executionContext) {
		contextoExecucao.salvarContexto(executionContext);
	}

	@Override
	public void close() {
		// Não utilizado.

	}

	@Override
	public void write(List<? extends UsuarioPO> usuarioPOs) throws Exception {
		usuarioBO.salvarTodos((List<UsuarioPO>) usuarioPOs);
		contextoExecucao
				.setRegistrosEscritos(contextoExecucao.getRegistrosEscritos() + usuarioPOs.size());
	}

}
