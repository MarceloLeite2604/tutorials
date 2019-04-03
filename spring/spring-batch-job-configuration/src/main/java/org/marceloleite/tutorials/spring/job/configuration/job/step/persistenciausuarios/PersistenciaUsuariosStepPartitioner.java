package org.marceloleite.tutorials.spring.job.configuration.job.step.persistenciausuarios;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.job.step.geral.DivisorArquivo;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistenciausuarios.contexto.PersistenciaClientesContextoPropriedade;
import org.marceloleite.tutorials.spring.job.configuration.propriedade.job.CriadorUsuariosContextoPropriedade;
import org.marceloleite.tutorials.spring.job.configuration.util.ArquivoUsuariosUtil;
import org.marceloleite.tutorials.spring.job.configuration.util.LocalDateTimeUtil;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class PersistenciaUsuariosStepPartitioner implements Partitioner {

	@Inject
	private DivisorArquivo divisorArquivo;

	@Inject
	private ArquivoUsuariosUtil arquivoUsuariosUtil;

	@Value("#{jobParameters}")
	private Map<String, Object> jobParameters;

	@Override
	public Map<String, ExecutionContext> partition(int numeroDivisoes) {

		String instante = (String)jobParameters.get(CriadorUsuariosContextoPropriedade.INSTANTE.getNome());

		LocalDateTime localDateTime = LocalDateTimeUtil.parseDataParaNomeArquivo(instante);

		String caminhoArquivoSaida = arquivoUsuariosUtil.elaborarCaminhoArquivo(localDateTime);

		List<String> caminhoArquivosTemporarios = divisorArquivo.dividirArquivo(caminhoArquivoSaida,
				numeroDivisoes);

		return montarMapaContextosExecucao(numeroDivisoes, caminhoArquivosTemporarios);
	}

	private Map<String, ExecutionContext> montarMapaContextosExecucao(int numeroDivisoes,
			List<String> caminhoArquivosTemporarios) {
		Map<String, ExecutionContext> resultado = new HashMap<>();
		for (int divisao = 0; divisao < numeroDivisoes; divisao++) {
			ExecutionContext executionContext = new ExecutionContext();
			executionContext.put(
					PersistenciaClientesContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS.getNome(),
					caminhoArquivosTemporarios.get(divisao));
			resultado.put(Integer.toString(divisao), executionContext);
		}
		return resultado;
	}

}
