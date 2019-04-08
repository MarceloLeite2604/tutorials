package com.github.marceloleite2604.tutorials.spring.job.configuration.util;

import java.util.Optional;

import org.springframework.batch.item.ExecutionContext;

import com.github.marceloleite2604.tutorials.spring.job.configuration.model.Propriedade;

public final class ExecutionContextUtil {

	private ExecutionContextUtil() {
		// Construtor privado para evitar a construção de objetos desta classe.
	}

	public static String obterPropriedade(Propriedade propriedade,
			ExecutionContext executionContext, String valorPadrao) {
		Optional<String> optionalValor = Optional
				.ofNullable((String) executionContext.get(propriedade.getNome()));

		if (optionalValor.isPresent()) {
			return optionalValor.get();
		} else {
			if (!propriedade.isObrigatorio()) {
				return propriedade.getValorPadrao();
			} else {
				if (valorPadrao != null) {
					return valorPadrao;
				} else {
					throw new RuntimeException("Não foi possível localizar a propriedade \""
							+ propriedade.getNome() + "\".");
				}
			}
		}
	}

	public static String obterPropriedadeIgnorandoValorPadrao(Propriedade propriedade,
			ExecutionContext executionContext) {
		Optional<String> optionalValor = Optional
				.ofNullable((String) executionContext.get(propriedade.getNome()));

		String valor = null;
		if (optionalValor.isPresent()) {
			valor = optionalValor.get();
		}

		return valor;
	}

	public static String obterPropriedade(Propriedade propriedade,
			ExecutionContext executionContext) {
		return obterPropriedade(propriedade, executionContext, null);
	}

}
