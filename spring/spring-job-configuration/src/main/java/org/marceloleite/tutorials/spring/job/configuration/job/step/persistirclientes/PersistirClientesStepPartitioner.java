package org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.job.DivisorArquivo;
import org.marceloleite.tutorials.spring.job.configuration.job.MeuJobProperties;
import org.marceloleite.tutorials.spring.job.configuration.job.step.persistirclientes.contexto.PersistirClientesContextoPropriedade;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class PersistirClientesStepPartitioner implements Partitioner {

	@Inject
	private DivisorArquivo divisorArquivo;

	@Inject
	private MeuJobProperties meuJobProperties;

	@Override
	public Map<String, ExecutionContext> partition(int numeroDivisoes) {

		List<String> caminhoArquivosTemporarios = divisorArquivo
				.dividirArquivo(meuJobProperties.getCaminhoArquivoDeUsuarios(), numeroDivisoes);

		return montarMapaContextosExecucao(numeroDivisoes, caminhoArquivosTemporarios);
	}

	private Map<String, ExecutionContext> montarMapaContextosExecucao(int numeroDivisoes,
			List<String> caminhoArquivosTemporarios) {
		Map<String, ExecutionContext> resultado = new HashMap<>();
		for (int divisao = 0; divisao < numeroDivisoes; divisao++) {
			ExecutionContext executionContext = new ExecutionContext();
			executionContext.put(
					PersistirClientesContextoPropriedade.CAMINHO_ARQUIVO_DE_USUARIOS.getNome(),
					caminhoArquivosTemporarios.get(divisao));
			resultado.put(Integer.toString(divisao), executionContext);
		}
		return resultado;
	}

}
