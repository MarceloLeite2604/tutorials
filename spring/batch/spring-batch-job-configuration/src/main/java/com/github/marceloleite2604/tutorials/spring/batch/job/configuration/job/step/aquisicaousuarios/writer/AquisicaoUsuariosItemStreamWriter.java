package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.aquisicaousuarios.writer;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.diversos.VerificadorLancamentoExcecaoSimulada;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.aquisicaousuarios.contexto.AquisicaoUsuariosContexto;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.csv.UsuarioCsvVO;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.enumeration.CriadorUsuariosContextoPropriedade;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util.ArquivoUsuariosUtil;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util.LocalDateTimeUtil;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class AquisicaoUsuariosItemStreamWriter implements ItemStreamWriter<UsuarioCsvVO> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AquisicaoUsuariosItemStreamWriter.class);

	private ArquivoUsuariosWriter usuarioCsvWriter;

	@Inject
	private AquisicaoUsuariosContexto contexto;

	@Inject
	private ArquivoUsuariosUtil arquivoUsuarios;
	
	@Inject
	private LocalDateTimeUtil localDateTimeUtil;

	@Inject
	private VerificadorLancamentoExcecaoSimulada verificadorLancamentoExcecaoSimulada;

	private JobParameters jobParameters;

	@BeforeStep
	public void beforeStep(final StepExecution stepExecution) {
		jobParameters = stepExecution.getJobParameters();
	}

	@Override
	public void write(List<? extends UsuarioCsvVO> usuarioCsvVOs) throws Exception {
		verificadorLancamentoExcecaoSimulada.verificar();
		usuarioCsvWriter.escrever(Collections.unmodifiableList(usuarioCsvVOs));
		contexto.setRegistrosEscritos(contexto.getRegistrosEscritos() + usuarioCsvVOs.size());
		LOGGER.debug("{} usuários escritos no arquivo CSV.", contexto.getRegistrosEscritos());
	}

	@Override
	public void open(ExecutionContext executionContext) {
		criarUsuarioCsvWriter();
	}

	private void criarUsuarioCsvWriter() {
		String textoInstante = jobParameters
				.getString(CriadorUsuariosContextoPropriedade.INSTANTE.getNome());

		LocalDateTime instante = localDateTimeUtil.parseDataParaNomeArquivo(textoInstante);

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
