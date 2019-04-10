package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.adquirente;

import java.util.Optional;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.Propriedade;

public interface AdquirentePropriedadeStrategy<T> {

	public Optional<String> adquirir(Propriedade propriedade, T origem);
}
