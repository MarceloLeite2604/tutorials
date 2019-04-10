package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.preparoambiente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.bo.UsuarioBO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.diversos.VerificadorLancamentoExcecaoSimulada;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.job.CriadorUsuariosContextoPropriedade;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util.ArquivoUsuariosUtil;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util.ArquivoUtil;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util.LocalDateTimeUtil;

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

		LocalDateTime instante = obterInstante(chunkContext.getStepContext()
				.getJobParameters())
				;
		excluirArquivosCsvDoInstante(instante);
		excluirUsuarios();
		
		return RepeatStatus.FINISHED;
	}

	private LocalDateTime obterInstante(Map<String, Object> parametrosJob) {
		String textoInstante = (String) parametrosJob
				.get(CriadorUsuariosContextoPropriedade.INSTANTE.getNome());
		return LocalDateTimeUtil.parseDataParaNomeArquivo(textoInstante);
	}

	private void excluirUsuarios() {
		usuarioBO.excluirTodos();
	}

	private void excluirArquivosCsvDoInstante(LocalDateTime instante) {
		List<String> arquivosDaData = arquivoUsuarios.obterCaminhoTodosArquivosDaData(instante);
		arquivoUtil.excluirArquivos(arquivosDaData);
	}

}
