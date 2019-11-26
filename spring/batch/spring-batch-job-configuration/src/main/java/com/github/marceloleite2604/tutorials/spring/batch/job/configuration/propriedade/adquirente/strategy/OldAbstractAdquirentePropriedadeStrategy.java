package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.propriedade.adquirente.strategy;

import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.model.Propriedade;
import java.util.Objects;
import java.util.Optional;

public abstract class OldAbstractAdquirentePropriedadeStrategy<T>
		implements OldAdquirentePropriedadeStrategy {

	private Class<T> classeContexto;

	private Optional<OldAdquirentePropriedadeStrategy> optionalProximo;

	protected OldAbstractAdquirentePropriedadeStrategy(Class<T> classeContexto) {
		this.classeContexto = classeContexto;
		this.optionalProximo = Optional.empty();
	}

	@Override
	public Optional<OldAdquirentePropriedadeStrategy> getProximo() {
		return optionalProximo;
	}

	@Override
	public void setProximo(OldAdquirentePropriedadeStrategy proximo) {
		this.optionalProximo = Optional.ofNullable(proximo);
	}

	protected boolean isInstanceOfClasseContexto(Object object) {
		return classeContexto.isInstance(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<String> obter(Propriedade propriedade, Object contexto) {

		if (!isInstanceOfClasseContexto(contexto)) {
			return obterProximo(propriedade, contexto);
		}

		String valor = doObter(propriedade, (T) contexto);

		if (!Objects.isNull(valor)) {
			return Optional.of(valor);
		}

		return obterProximo(propriedade, contexto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean definir(Propriedade propriedade, String valor, Object contexto) {
		if (isInstanceOfClasseContexto(contexto)) {
			return doDefinir(propriedade, valor, (T) contexto);
		}

		return definirProximo(propriedade, valor, contexto);
	}

	private Optional<String> obterProximo(Propriedade propriedade, Object contexto) {
		return optionalProximo.map(proximo -> proximo.obter(propriedade, contexto))
				.orElse(Optional.empty());
	}

	private boolean definirProximo(Propriedade propriedade, String valor, Object contexto) {
		return optionalProximo.map(proximo -> proximo.definir(propriedade, valor, contexto))
				.orElse(false);
	}

	protected abstract String doObter(Propriedade propriedade, T contexto);

	protected abstract boolean doDefinir(Propriedade propriedade, String valor, T contexto);

}
