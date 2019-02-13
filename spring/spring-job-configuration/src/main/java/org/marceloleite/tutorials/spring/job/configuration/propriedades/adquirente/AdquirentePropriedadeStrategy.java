package org.marceloleite.tutorials.spring.job.configuration.propriedades.adquirente;

import java.util.Optional;

import org.marceloleite.tutorials.spring.job.configuration.model.propriedades.Propriedade;

public interface AdquirentePropriedadeStrategy<T> {

	public Optional<String> adquirir(Propriedade propriedade, T origem);
}
