package org.marceloleite.tutorials.spring.job.configuration.propriedades.adquirente;

import java.util.Optional;
import java.util.Properties;

import org.marceloleite.tutorials.spring.job.configuration.model.propriedades.Propriedade;
import org.springframework.stereotype.Component;

@Component
public class AdquirentePropriedadePropertyStrategy
		implements AdquirentePropriedadeStrategy<Properties> {

	@Override
	public Optional<String> adquirir(Propriedade propriedade, Properties properties) {
		return Optional.ofNullable(properties.getProperty(propriedade.getNome()));
	}

}
