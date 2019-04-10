package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.geral;

import java.util.Optional;
import java.util.Properties;

import javax.inject.Inject;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.exception.SpringBatchJobConfigurationRuntimeException;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.Propriedade;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.adquirente.AdquirentePropriedadeExecutionContextStrategy;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.adquirente.AdquirentePropriedadePropertyStrategy;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.adquirente.AdquirentePropriedadeValorPadraoStrategy;

@Component
public class AdquirentePropriedadeContexto {

	@Inject
	private AdquirentePropriedadeExecutionContextStrategy executionContextStrategy;

	@Inject
	private AdquirentePropriedadePropertyStrategy propertyStrategy;

	@Inject
	private AdquirentePropriedadeValorPadraoStrategy valorPadraoStrategy;

	public String adquirir(Propriedade propriedade, ExecutionContext executionContext,
			Properties properties, String valorPadrao) {

		Optional<String> optionalValor = executionContextStrategy.adquirir(propriedade,
				executionContext);

		if (optionalValor.isPresent()) {
			return optionalValor.get();
		}

		optionalValor = propertyStrategy.adquirir(propriedade, properties);

		if (optionalValor.isPresent()) {
			return optionalValor.get();
		}

		optionalValor = valorPadraoStrategy.adquirir(propriedade, propriedade);

		if (optionalValor.isPresent()) {
			return optionalValor.get();
		}

		if (valorPadrao != null) {
			return valorPadrao;
		}

		throw new SpringBatchJobConfigurationRuntimeException("Não foi possível localizar a propriedade \""
				+ propriedade.getNomeCompleto() + "\".");
	}

	public String adquirir(Propriedade propriedade, ExecutionContext executionContext,
			Properties properties) {
		return adquirir(propriedade, executionContext, properties, null);
	}
}
