package com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.adquirente;

import java.util.Optional;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.job.configuration.model.Propriedade;

@Component
public class AdquirentePropriedadeExecutionContextStrategy
		implements AdquirentePropriedadeStrategy<ExecutionContext> {

	@Override
	public Optional<String> adquirir(Propriedade propriedade, ExecutionContext executionContext) {
		return Optional.ofNullable((String)executionContext.get(propriedade.getNome()));
	}

}
