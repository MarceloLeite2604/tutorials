package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.job.step.geral;

import java.util.Properties;

import javax.inject.Inject;

import org.springframework.batch.item.ExecutionContext;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.Propriedade;

public abstract class AbstractContexto {

	@Inject
	private AdquirentePropriedadeContexto adquirentePropriedadeContexto;

	protected String adquirir(Propriedade propriedade, ExecutionContext executionContext) {
		return adquirentePropriedadeContexto.adquirir(propriedade, executionContext,
				obterProperties());
	}
	
	protected String adquirir(Propriedade propriedade, ExecutionContext executionContext, String valorPadrao) {
		return adquirentePropriedadeContexto.adquirir(propriedade, executionContext,
				obterProperties(), valorPadrao);
	}
	
	protected void definir(Propriedade propriedade, String valor, ExecutionContext executionContext) {
		executionContext.put(propriedade.getNome(), valor);
	}

	protected abstract Properties obterProperties();

}
