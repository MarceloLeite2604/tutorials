package org.marceloleite.tutorials.spring.job.configuration.util;

import java.util.Optional;
import java.util.Properties;

import org.marceloleite.tutorials.spring.job.configuration.mapper.ExecutionContextParaPropertiesMapper;
import org.marceloleite.tutorials.spring.job.configuration.model.propriedades.Propriedade;
import org.springframework.batch.item.ExecutionContext;

public final class PropriedadeUtil {

	private static final ExecutionContextParaPropertiesMapper EXECUTION_CONTEXT_PARA_PROPERTIES_MAPPER = new ExecutionContextParaPropertiesMapper();

	private PropriedadeUtil() {
		// Construtor privado para evitar a instanciação de objetos desta classe.
	}

	public static String obterPropriedade(Propriedade propriedade, Properties properties,
			String valorPadrao) {
		Optional<String> optionalValorPropriedade = Optional
				.ofNullable(properties.getProperty(propriedade.getNome()));

		if (optionalValorPropriedade.isPresent()) {
			return optionalValorPropriedade.get();
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

	public static String obterPropriedade(Propriedade propriedade, Properties properties) {
		return obterPropriedade(propriedade, properties, null);
	}

	public static String obterPropriedade(Propriedade propriedade,
			ExecutionContext executionContext, String valorPadrao) {
		return obterPropriedade(propriedade,
				EXECUTION_CONTEXT_PARA_PROPERTIES_MAPPER.map(executionContext), valorPadrao);
	}

	public static String obterPropriedade(Propriedade propriedade,
			ExecutionContext executionContext) {
		return obterPropriedade(propriedade,
				EXECUTION_CONTEXT_PARA_PROPERTIES_MAPPER.map(executionContext), null);
	}
}
