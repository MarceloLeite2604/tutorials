package com.github.marceloleite2604.tutorials.spring.batch.job.configuration.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class LocalDateTimeUtil {

	private static final DateTimeFormatter DATE_TIME_FORMATTER_NOME_DE_ARQUIVO = DateTimeFormatter
			.ofPattern("yyyy-MM-dd-kk-mm");

	private LocalDateTimeUtil() {
		// Construtor privado para evitar a criação de objetos desta classe.
	}

	public static final String formatarParaNomeDeArquivo(LocalDateTime localDateTime) {
		return localDateTime.format(DATE_TIME_FORMATTER_NOME_DE_ARQUIVO);
	}

	public static LocalDateTime parseDataParaNomeArquivo(String texto) {
		return LocalDateTime.parse(texto, DATE_TIME_FORMATTER_NOME_DE_ARQUIVO);
	}

}
