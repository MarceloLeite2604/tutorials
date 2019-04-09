package com.github.marceloleite2604.tutorials.spring.job.configuration.job.step.limpezaambiente;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.job.configuration.diversos.VerificadorLancamentoExcecaoSimulada;
import com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.job.CriadorUsuariosContextoPropriedade;
import com.github.marceloleite2604.tutorials.spring.job.configuration.util.ArquivoUsuariosUtil;
import com.github.marceloleite2604.tutorials.spring.job.configuration.util.ArquivoUtil;
import com.github.marceloleite2604.tutorials.spring.job.configuration.util.LocalDateTimeUtil;

@Component
public class LimpezaAmbienteTasklet implements Tasklet {

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
		return RepeatStatus.FINISHED;
	}

	private void excluirArquivosDaData(Map<String, Object> parametrosJob) {
		String instante = (String) parametrosJob
				.get(CriadorUsuariosContextoPropriedade.INSTANTE.getNome());
		List<String> arquivosDaData = arquivoUsuarios.obterCaminhoArquivosTemporariosDaData(
				LocalDateTimeUtil.parseDataParaNomeArquivo(instante));
		arquivoUtil.excluirArquivos(arquivosDaData);
	}

}
