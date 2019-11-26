package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.adquirente.strategy;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.Propriedade;
import java.util.Properties;
import org.springframework.stereotype.Component;

@Component
public class OldAdquirentePropriedadePropertyStrategy
		extends OldAbstractAdquirentePropriedadeStrategy<Properties> {

	public OldAdquirentePropriedadePropertyStrategy() {
		super(Properties.class);
	}

	@Override
	protected String doObter(Propriedade propriedade, Properties contexto) {
		return contexto.getProperty(propriedade.getNome());
	}

	@Override
	protected boolean doDefinir(Propriedade propriedade, String valor, Properties contexto) {
		contexto.setProperty(propriedade.getNome(), valor);
		return true;
	}
}
