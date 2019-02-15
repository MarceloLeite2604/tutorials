package org.marceloleite.tutorials.spring.job.configuration.propriedade.adquirente;

import java.util.Optional;

import org.marceloleite.tutorials.spring.job.configuration.model.Propriedade;

public interface AdquirentePropriedadeStrategy<T> {

	public Optional<String> adquirir(Propriedade propriedade, T origem);
}
