package com.github.marceloleite2604.tutorials.spring.job.configuration.job.step.aquisicaousuarios.writer;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.job.configuration.diversos.VerificadorLancamentoExcecaoSimulada;
import com.github.marceloleite2604.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.job.CriadorUsuariosContextoPropriedade;
import com.github.marceloleite2604.tutorials.spring.job.configuration.util.ArquivoUsuariosUtil;
import com.github.marceloleite2604.tutorials.spring.job.configuration.util.LocalDateTimeUtil;

@Component
@StepScope
public class AquisicaoUsuariosItemStreamWriter implements ItemStreamWriter<UsuarioCsvVO> {

	private ArquivoUsuariosWriter usuarioCsvWriter;

	@Inject
	private ArquivoUsuariosUtil arquivoUsuarios;

	private JobParameters jobParameters;
	
	@Inject
	private VerificadorLancamentoExcecaoSimulada verificadorLancamentoExcecaoSimulada;

	@BeforeStep
	public void beforeStep(final StepExecution stepExecution) {
		jobParameters = stepExecution.getJobParameters();
	}

	@Override
	public void write(List<? extends UsuarioCsvVO> usuarioCsvVOs) throws Exception {
		verificadorLancamentoExcecaoSimulada.verificar();
		usuarioCsvWriter.escrever(Collections.unmodifiableList(usuarioCsvVOs));
	}

	@Override
	public void open(ExecutionContext executionContext) {
		criarUsuarioCsvWriter();
	}

	private void criarUsuarioCsvWriter() {
		String textoInstante = jobParameters
				.getString(CriadorUsuariosContextoPropriedade.INSTANTE.getNome());

		LocalDateTime instante = LocalDateTimeUtil.parseDataParaNomeArquivo(textoInstante);

		String caminhoArquivoSaida = arquivoUsuarios.elaborarCaminhoArquivo(instante);

		usuarioCsvWriter = new ArquivoUsuariosWriter(caminhoArquivoSaida);
	}

	@Override
	public void update(ExecutionContext executionContext) {
		// Não utilizado.

	}

	@Override
	public void close() {
		// Não utilizado.
	}

}
