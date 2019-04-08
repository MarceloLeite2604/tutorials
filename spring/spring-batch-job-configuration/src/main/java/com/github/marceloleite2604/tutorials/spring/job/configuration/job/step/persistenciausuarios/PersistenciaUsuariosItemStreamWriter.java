package com.github.marceloleite2604.tutorials.spring.job.configuration.job.step.persistenciausuarios;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.job.configuration.bo.UsuarioBO;
import com.github.marceloleite2604.tutorials.spring.job.configuration.diversos.VerificadorLancamentoExcecaoSimulada;
import com.github.marceloleite2604.tutorials.spring.job.configuration.job.step.persistenciausuarios.contexto.PersistenciaClientesContexto;
import com.github.marceloleite2604.tutorials.spring.job.configuration.model.po.UsuarioPO;

@Component
public class PersistenciaUsuariosItemStreamWriter implements ItemStreamWriter<UsuarioPO> {

	@Inject
	private UsuarioBO usuarioBO;

	@Inject
	private PersistenciaClientesContexto contextoExecucao;
	
	@Inject
	private VerificadorLancamentoExcecaoSimulada verificadorLancamentoExcecaoSimulada;

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
		verificadorLancamentoExcecaoSimulada.verificar();
		usuarioBO.salvarTodos(Collections.unmodifiableList(usuarioPOs));
		contextoExecucao
				.setRegistrosEscritos(contextoExecucao.getRegistrosEscritos() + usuarioPOs.size());
	}

}
