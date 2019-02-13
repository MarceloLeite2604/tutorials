package org.marceloleite.tutorials.spring.job.configuration.job.step.geral;

import java.util.Optional;
import java.util.Properties;

import javax.inject.Inject;

import org.marceloleite.tutorials.spring.job.configuration.model.propriedades.Propriedade;
import org.marceloleite.tutorials.spring.job.configuration.propriedades.adquirente.AdquirentePropriedadeExecutionContextStrategy;
import org.marceloleite.tutorials.spring.job.configuration.propriedades.adquirente.AdquirentePropriedadePropertyStrategy;
import org.marceloleite.tutorials.spring.job.configuration.propriedades.adquirente.AdquirentePropriedadeValorPadraoStrategy;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class AdquirentePropriedadeContexto {

	@Inject
	private AdquirentePropriedadeExecutionContextStrategy executionContextStrategy;

	@Inject
	private AdquirentePropriedadePropertyStrategy propertyStrategy;

	@Inject
	private AdquirentePropriedadeValorPadraoStrategy valorPadraoStrategy;

	public String adquirir(Propriedade propriedade, ExecutionContext executionContext,
			Properties properties) {

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

		throw new RuntimeException("Não foi possível localizar a propriedade \""
				+ propriedade.getNomeCompleto() + "\".");
	}
}
