package org.marceloleite.tutorials.spring.job.configuration.util;

import java.util.Optional;
import java.util.Properties;

import org.marceloleite.tutorials.spring.job.configuration.model.propriedades.Propriedade;

public final class PropriedadeUtil {

	private PropriedadeUtil() {
		// Construtor privado para evitar a instanciação de objetos desta classe.
	}

	public static String obter(Propriedade propriedade, Properties properties,
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

	public static String obter(Propriedade propriedade, Properties properties) {
		return obter(propriedade, properties, null);
	}
	
	public static void copiar(Properties propertiesOrigem, Propriedade propriedadeOrigem,
			Properties propertiesDestino, Propriedade propriedadeDestino) {
		propertiesDestino.put(propriedadeDestino.getNome(),
				propertiesOrigem.get(propriedadeOrigem.getNome()));
	}
}
