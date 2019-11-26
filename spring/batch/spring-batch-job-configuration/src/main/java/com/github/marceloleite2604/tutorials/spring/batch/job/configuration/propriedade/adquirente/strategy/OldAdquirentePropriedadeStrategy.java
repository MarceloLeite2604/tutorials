package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.adquirente.strategy;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.Propriedade;
import java.util.Optional;

public interface OldAdquirentePropriedadeStrategy {

	public Optional<String> obter(Propriedade propriedade, Object contexto);
	
	public boolean definir(Propriedade propriedade, String valor, Object contexto);
	
	Optional<OldAdquirentePropriedadeStrategy> getProximo();
	
	void setProximo(OldAdquirentePropriedadeStrategy proximo);
}
