package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.persistenciausuarios;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.geral.DivisorArquivo;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.persistenciausuarios.contexto.PersistenciaClientesContextoPropriedade;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.job.CriadorUsuariosContextoPropriedade;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util.ArquivoUsuariosUtil;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util.LocalDateTimeUtil;

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

		String instante = (String) jobParameters
				.get(CriadorUsuariosContextoPropriedade.INSTANTE.getNome());

		LocalDateTime localDateTime = LocalDateTimeUtil.parseDataParaNomeArquivo(instante);

		String caminhoArquivoSaida = arquivoUsuariosUtil.elaborarCaminhoArquivo(localDateTime);

		Map<String, Long> totalRegistrosPorArquivosTemporarios = divisorArquivo
				.dividirArquivo(caminhoArquivoSaida, numeroDivisoes);

		return montarMapaContextosExecucao(totalRegistrosPorArquivosTemporarios);
	}

	private Map<String, ExecutionContext> montarMapaContextosExecucao(
			Map<String, Long> totalRegistrosPorArquivosTemporarios) {
		int divisao = 0;
		Map<String, ExecutionContext> resultado = new HashMap<>();
		for (Entry<String, Long> entry : totalRegistrosPorArquivosTemporarios.entrySet()) {
			ExecutionContext executionContext = new ExecutionContext();
			executionContext.put(
					PersistenciaClientesContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS.getNome(),
					entry.getKey());
			executionContext.put(
					PersistenciaClientesContextoPropriedade.TOTAL_DE_REGISTROS.getNome(),
					Long.toString(entry.getValue()));
			resultado.put(Integer.toString(divisao++), executionContext);
		}
		return resultado;
	}

}
