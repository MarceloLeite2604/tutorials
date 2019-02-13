package org.marceloleite.tutorials.spring.job.configuration.propriedades.adquirente;

import java.util.Optional;

import org.marceloleite.tutorials.spring.job.configuration.model.propriedades.Propriedade;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class AdquirentePropriedadeExecutionContextStrategy
		implements AdquirentePropriedadeStrategy<ExecutionContext> {

	@Override
	public Optional<String> adquirir(Propriedade propriedade, ExecutionContext executionContext) {
		return Optional.ofNullable((String)executionContext.get(propriedade.getNome()));
	}

}
