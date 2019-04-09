package com.github.marceloleite2604.tutorials.spring.job.configuration.propriedade.adquirente;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.github.marceloleite2604.tutorials.spring.job.configuration.model.Propriedade;

@Component
public class AdquirentePropriedadeValorPadraoStrategy
		implements AdquirentePropriedadeStrategy<Propriedade> {

	@Override
	public Optional<String> adquirir(Propriedade propriedade, Propriedade propriedadeOrigem) {
		return Optional.ofNullable(propriedade.getValorPadrao());
	}

}
