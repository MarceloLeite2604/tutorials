package org.marceloleite.tutorials.spring.job.configuration.job.step.aquisicaousuarios.writer;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.model.csv.UsuarioCsvVO;
import org.marceloleite.tutorials.spring.job.configuration.propriedade.job.CriadorUsuariosContextoPropriedade;
import org.marceloleite.tutorials.spring.job.configuration.util.ArquivoUsuariosUtil;
import org.marceloleite.tutorials.spring.job.configuration.util.LocalDateTimeUtil;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class AquisicaoUsuariosItemStreamWriter implements ItemStreamWriter<UsuarioCsvVO> {

	private ArquivoUsuariosWriter usuarioCsvWriter;

	@Inject
	private ArquivoUsuariosUtil arquivoUsuarios;

	private JobParameters jobParameters;

	@BeforeStep
	public void beforeStep(final StepExecution stepExecution) {
		jobParameters = stepExecution.getJobParameters();
	}

	@Override
	public void write(List<? extends UsuarioCsvVO> usuarioCsvVOs) throws Exception {
		usuarioCsvWriter.escrever((List<UsuarioCsvVO>) usuarioCsvVOs);
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		criarUsuarioCsvWriter();
	}

	private void criarUsuarioCsvWriter() {
		String instante = jobParameters.getString(CriadorUsuariosContextoPropriedade.INSTANTE.getNome());

		LocalDateTime localDateTime = LocalDateTimeUtil.parseDataParaNomeArquivo(instante);

		String caminhoArquivoSaida = arquivoUsuarios.elaborarCaminhoArquivo(localDateTime);

		usuarioCsvWriter = new ArquivoUsuariosWriter(caminhoArquivoSaida);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws ItemStreamException {
		// TODO Auto-generated method stub

	}

}
