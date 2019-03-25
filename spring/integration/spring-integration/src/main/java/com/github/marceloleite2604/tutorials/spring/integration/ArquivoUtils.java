package com.github.marceloleite2604.tutorials.spring.integration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.marceloleite2604.tutorials.spring.integration.configuration.ProgramaConfiguration;

public final class ArquivoUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArquivoUtils.class);
	
	private ArquivoUtils() {
		// Construtor privado para evitar a criação de objetos desta classe.
	}

	public static final void excluirArquivosJpgDiretorioSaida() {
		Arrays.asList(Paths.get(ProgramaConfiguration.CAMINHO_DIRETORIO_SAIDA)
				.toFile()
				.listFiles())
				.stream()
				.filter(file -> file.getName()
						.endsWith(".jpg"))
				.forEach(ArquivoUtils::excluirArquivo);	
	}
	
	private static void excluirArquivo(File arquivo) {
		LOGGER.info("Excluindo o arquivo \"{}\".", arquivo.getName());
		try {
			Files.delete(Paths.get(arquivo.getPath()));
		} catch (IOException excecao) {
			throw new RuntimeException(
					"Falha ao excluir o arquivo \"" + arquivo.getAbsolutePath() + "\".", excecao);
		}
	}
}
