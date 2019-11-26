package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.bo;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.exception.SpringBatchJobConfigurationRuntimeException;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.Propriedade;
import java.util.Objects;
import java.util.Optional;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class PropriedadeBO {

	private static final String TEMPLATE_MENSAGEM_PROPRIEDADE_NAO_ENCONTRADA = "Não foi possível localizar a propriedade \"%s\".";

	private static final String TEMPLATE_MENSAGEM_PROPRIEDADE_NAO_EH_DO_TIPO = "Propriedade \"%s\" obtida pelo contexto de execução não é do tipo \"%s\".";

	public String obter(Propriedade propriedade, ExecutionContext executionContext) {
		return obter(propriedade, executionContext, null);
	}

	public String obter(Propriedade propriedade, ExecutionContext executionContext,
			String valorPadrao) {
		String nome = propriedade.getNome();
		Object valor = executionContext.get(nome);

		if (!Objects.isNull(valor) && !(valor instanceof String)) {
			String mensagem = String.format(TEMPLATE_MENSAGEM_PROPRIEDADE_NAO_EH_DO_TIPO, nome,
					String.class.getSimpleName());
			throw new SpringBatchJobConfigurationRuntimeException(mensagem);
		}

		String stringValor = (String) valor;

		return Optional.ofNullable(stringValor)
				.orElse(valorPadrao);
	}

	public String obterObrigatorio(Propriedade propriedade, ExecutionContext executionContext) {
		return Optional.ofNullable(obter(propriedade, executionContext))
				.orElseThrow(() -> criarExcecaoPropriedadeNaoEncontrada(propriedade));
	}

	private RuntimeException criarExcecaoPropriedadeNaoEncontrada(Propriedade propriedade) {
		String mensagem = String.format(TEMPLATE_MENSAGEM_PROPRIEDADE_NAO_ENCONTRADA,
				propriedade.getNome());
		return new SpringBatchJobConfigurationRuntimeException(mensagem);
	}

	public void definir(Propriedade propriedade, String valor, ExecutionContext executionContext) {
		executionContext.put(propriedade.getNome(), valor);
	}
}
