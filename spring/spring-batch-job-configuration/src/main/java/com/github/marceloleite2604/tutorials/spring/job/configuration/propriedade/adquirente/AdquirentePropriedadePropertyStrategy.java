package com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.adquirente;

import java.util.Optional;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.job.configuration.model.Propriedade;

@Component
public class AdquirentePropriedadePropertyStrategy
		implements AdquirentePropriedadeStrategy<Properties> {

	@Override
	public Optional<String> adquirir(Propriedade propriedade, Properties properties) {
		return Optional.ofNullable(properties.getProperty(propriedade.getNome()));
	}

}
