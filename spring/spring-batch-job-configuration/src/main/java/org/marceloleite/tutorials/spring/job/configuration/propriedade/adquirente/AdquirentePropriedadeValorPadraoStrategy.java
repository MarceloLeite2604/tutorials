package org.marceloleite.tutorials.spring.job.configuration.propriedade.adquirente;

import java.util.Optional;

import org.marceloleite.tutorials.spring.job.configuration.model.Propriedade;
import org.springframework.stereotype.Component;

@Component
public class AdquirentePropriedadeValorPadraoStrategy
		implements AdquirentePropriedadeStrategy<Propriedade> {

	@Override
	public Optional<String> adquirir(Propriedade propriedade, Propriedade propriedadeOrigem) {
		return Optional.ofNullable(propriedade.getValorPadrao());
	}

}
