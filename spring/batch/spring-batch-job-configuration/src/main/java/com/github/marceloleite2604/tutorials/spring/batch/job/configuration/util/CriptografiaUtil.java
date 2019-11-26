package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util;

import com.github.marceloleite2604.sled.Sled;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.exception.SpringBatchJobConfigurationRuntimeException;
import com.github.marceloleite2604.tutorials.spring.batch.job.configuration.properties.CriptografiaProperties;
import java.util.Objects;
import java.util.Optional;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CriptografiaUtil {

	private static final String MENSAGEM_CHAVE_CRIPTOGRAFIA_NAO_ENCONTRADA = "A chave de criptografia não foi informada.";

	private static final String TEMPLATE_MENSAGEM_ERRO_AQUISICAO_CHAVE_VARIAVEL_AMBIENTE = "Ocorreu um erro ao tentar obter a chave de criptografia a partir da propriedade \"%s\".";

	private static final String TEMPLATE_MENSAGEM_VARIAVEL_AMBIENTE_CHAVE_CRIPTOGRAFIA_VAZIA = "A variável de ambiente \"%s\" que deveria conter a chave de criptografia está vazia..";

	@Inject
	private CriptografiaProperties criptografiaPropriedades;

	@Inject
	private Sled sled;

	private String chave;

	public String criptografar(String texto) {
		return sled.encrypt(texto, obterChave());
	}

	public String decriptografar(String texto) {
		return sled.decrypt(texto, obterChave());
	}

	private String obterChave() {
		if (StringUtils.isEmpty(chave)) {
			chave = buscarChave();
		}
		return chave;
	}

	private String buscarChave() {
		String chaveObtida = Optional.ofNullable(criptografiaPropriedades.getChave())
				.orElse(obterChaveVariavelAmbiente());
		
		if (StringUtils.isEmpty(chaveObtida)) {
			throw new SpringBatchJobConfigurationRuntimeException(
					MENSAGEM_CHAVE_CRIPTOGRAFIA_NAO_ENCONTRADA);
		}
		
		return chaveObtida;
	}

	private String obterChaveVariavelAmbiente() {

		if (Objects.isNull(criptografiaPropriedades.getNomeVariavelAmbienteChave())) {
			return null;
		}

		try {
			String chaveVariavelAmbiente = System
					.getenv(criptografiaPropriedades.getNomeVariavelAmbienteChave());

			if (StringUtils.isEmpty(chaveVariavelAmbiente)) {
				String mensagem = String.format(
						TEMPLATE_MENSAGEM_VARIAVEL_AMBIENTE_CHAVE_CRIPTOGRAFIA_VAZIA,
						criptografiaPropriedades.getNomeVariavelAmbienteChave());
				throw new SpringBatchJobConfigurationRuntimeException(mensagem);
			}

			return chaveVariavelAmbiente;
		} catch (SecurityException excecao) {
			throw new SpringBatchJobConfigurationRuntimeException(
					TEMPLATE_MENSAGEM_ERRO_AQUISICAO_CHAVE_VARIAVEL_AMBIENTE, excecao);
		}
	}
}
