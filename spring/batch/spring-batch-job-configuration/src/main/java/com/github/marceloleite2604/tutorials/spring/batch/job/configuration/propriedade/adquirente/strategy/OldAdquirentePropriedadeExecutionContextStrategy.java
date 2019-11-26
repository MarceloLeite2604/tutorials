package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.adquirente.strategy;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.exception.SpringBatchJobConfigurationRuntimeException;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.Propriedade;
import java.util.Objects;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class OldAdquirentePropriedadeExecutionContextStrategy
		extends OldAbstractAdquirentePropriedadeStrategy<ExecutionContext> {

	public OldAdquirentePropriedadeExecutionContextStrategy() {
		super(ExecutionContext.class);
	}

	private static final String TEMPLATE_MENSAGEM_PROPRIEDADE_NAO_EH_DO_TIPO = "Propriedade \"%s\" obtida pelo contexto de execução não é do tipo \"%s\".";

	@Override
	public String doObter(Propriedade propriedade, ExecutionContext contexto) {
		String nome = propriedade.getNome();
		Object valor = contexto.get(nome);

		if (!Objects.isNull(valor) && !(valor instanceof String)) {
			String mensagem = String.format(TEMPLATE_MENSAGEM_PROPRIEDADE_NAO_EH_DO_TIPO, nome,
					String.class.getSimpleName());
			throw new SpringBatchJobConfigurationRuntimeException(mensagem);
		}

		return (String) valor;
	}

	@Override
	protected boolean doDefinir(Propriedade propriedade, String valor, ExecutionContext contexto) {
		contexto.put(propriedade.getNome(), valor);
		return true;
	}

}
