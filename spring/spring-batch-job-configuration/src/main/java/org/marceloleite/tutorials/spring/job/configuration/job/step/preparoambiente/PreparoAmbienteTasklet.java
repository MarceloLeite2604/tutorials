package org.marceloleite.tutorials.spring.job.configuration.job.step.preparoambiente;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.bo.UsuarioBO;
import org.marceloleite.tutorials.spring.job.configuration.diversos.VerificadorLancamentoExcecaoSimulada;
import org.marceloleite.tutorials.spring.job.configuration.propriedade.job.CriadorUsuariosContextoPropriedade;
import org.marceloleite.tutorials.spring.job.configuration.util.ArquivoUsuariosUtil;
import org.marceloleite.tutorials.spring.job.configuration.util.ArquivoUtil;
import org.marceloleite.tutorials.spring.job.configuration.util.LocalDateTimeUtil;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class PreparoAmbienteTasklet implements Tasklet {

	@Inject
	private UsuarioBO usuarioBO;

	@Inject
	private ArquivoUsuariosUtil arquivoUsuarios;

	@Inject
	private ArquivoUtil arquivoUtil;
	
	@Inject
	private VerificadorLancamentoExcecaoSimulada verificadorLancamentoExcecaoSimulada;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
			throws Exception {
		verificadorLancamentoExcecaoSimulada.verificar();
		excluirArquivosDaData(chunkContext.getStepContext()
				.getJobParameters());
		excluirUsuarios();
		return RepeatStatus.FINISHED;
	}

	private void excluirUsuarios() {
		usuarioBO.excluirTodos();
	}

	private void excluirArquivosDaData(Map<String, Object> parametrosJob) {
		String instante = (String) parametrosJob.get(CriadorUsuariosContextoPropriedade.INSTANTE.getNome());
		List<String> arquivosDaData = arquivoUsuarios.obterCaminhoTodosArquivosDaData(
				LocalDateTimeUtil.parseDataParaNomeArquivo(instante));
		arquivoUtil.excluirArquivos(arquivosDaData);
	}

}
